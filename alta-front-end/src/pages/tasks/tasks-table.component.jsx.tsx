import {TaskDto, TasksResponse} from "../../api/tasks/dto/tasks-response.dto.ts";
import {GridRowId} from "@mui/x-data-grid";
import {FC, useState} from "react";
import {Checkbox, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import {ImageRender} from "./image.tsx";
import BasicModal from "./modal.tsx";
import {Button} from '../../components/buttons/green-button.component.tsx';
import {api} from "../../core/api.ts";
import {useSelectedTopics, useStudents} from "../../context/data-provider.context.tsx";
import {useNavigate} from "react-router-dom";

interface TasksTableProps {
    tasks: TasksResponse;
    selectedTaskIds: TaskDto[];
    setSelectedTaskIds: (value: TaskDto[]) => void;
}

export const TasksTable: FC<TasksTableProps> = ({tasks, setSelectedTaskIds, selectedTaskIds}) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selectedTask, setSelectedTask] = useState<TaskDto | null>(null);

    const navigate = useNavigate();
    const {selectedStudentIds} = useStudents();
    const {selectedTopics, setSelectedTopics} = useSelectedTopics();

    const handleToggleModal = (task: TaskDto) => {
        setSelectedTask(task); // Set the selected task
        setIsOpen(true); // Open the modal
    };

    const closeModal = () => {
        setIsOpen(false); // Закриваємо модальне вікно
        setSelectedTask(null); // Очищуємо обране завдання
    };

    const handleUnfinishedTasksSelection = (selectedRows: GridRowId[]) => {
        const selectedTasks = selectedRows.map((row) => {
            const taskId = Number(row);
            return tasks.unfinishedTasksForAllStudentsSelected.find(task => task.id === taskId);
        }).filter(Boolean) as TaskDto[];

        // Перевірка, чи елемент вже вибраний
        const newSelectedTasks = selectedTaskIds.some(task => selectedTasks.some(selectedTask => selectedTask.id === task.id))
            ? selectedTaskIds.filter((task) => !selectedTasks.some(selectedTask => selectedTask.id === task.id))  // Видалення, якщо вже вибраний
            : [...selectedTaskIds, ...selectedTasks];  // Додавання, якщо ще не вибраний

        setSelectedTaskIds(newSelectedTasks);
    };

    const handleCompletedTasksSelection = (selectedRows: GridRowId[]) => {
        const selectedTasks = selectedRows.map((row) => {
            const taskId = Number(row);
            return tasks.tasksCompletedByAtLeastOneStudent.find(task => task.id === taskId);
        }).filter(Boolean) as TaskDto[];

        // Перевірка, чи елемент вже вибраний
        const newSelectedTasks = selectedTaskIds.some(task => selectedTasks.some(selectedTask => selectedTask.id === task.id))
            ? selectedTaskIds.filter((task) => !selectedTasks.some(selectedTask => selectedTask.id === task.id))  // Видалення, якщо вже вибраний
            : [...selectedTaskIds, ...selectedTasks];  // Додавання, якщо ще не вибраний

        setSelectedTaskIds(newSelectedTasks);
    };

    const reloadTableData = async () => {
        console.log('selected topics:', selectedTopics);
        try {
            const response = await api.post('/tasks/unfinished', {
                topics: selectedTopics,
                students: selectedStudentIds,
            });
            const updatedTasks = response.data;
            navigate('/tasks', {state: {tasks: updatedTasks}});
        } catch (error) {
            console.error('Error reloading tasks data:', error);
        }
    };

    return (
        <TableContainer>
            <Table sx={{minWidth: 850}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell padding="checkbox"/>
                        <TableCell>Тема</TableCell>
                        <TableCell>Рівень</TableCell>
                        <TableCell>Завдання</TableCell>
                        <TableCell>Відповідь</TableCell>
                        <TableCell/>
                    </TableRow>
                </TableHead>
                <TableBody>

                    {tasks.unfinishedTasksForAllStudentsSelected.map((task) => (
                        <TableRow
                            key={`unfinished-task-key-${task.id}`}
                            sx={{'&:last-child td, &:last-child th': {border: 0}, cursor: 'pointer'}}
                        >
                            <TableCell padding="checkbox">
                                <Checkbox
                                    checked={selectedTaskIds.some(selectedTask => selectedTask.id === task.id)}
                                    onClick={() => handleUnfinishedTasksSelection([task.id])}
                                />
                            </TableCell>
                            <TableCell component="th" scope="row">
                                {task.title}
                            </TableCell>
                            <TableCell>{task.level}</TableCell>
                            <TableCell>
                                <ImageRender value={task.imagePath}/>
                            </TableCell>
                            <TableCell>{task.answer}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleToggleModal(task)} color={"green"} label={"Редагувати"}/>
                            </TableCell>
                        </TableRow>
                    ))}
                    {tasks.tasksCompletedByAtLeastOneStudent.map((task) => (
                        <TableRow
                            key={`completed-task-key-${task.id}`}
                            sx={{
                                '&:last-child td, &:last-child th': {border: 0},
                                cursor: 'pointer',
                                backgroundColor: 'lightgrey'
                        }}
                        >
                            <TableCell padding="checkbox">
                                <Checkbox
                                    checked={selectedTaskIds.some(selectedTask => selectedTask.id === task.id)}
                                    onClick={() => handleCompletedTasksSelection([task.id])}
                                />
                            </TableCell>
                            <TableCell component="th" scope="row">
                                {task.title}
                            </TableCell>
                            <TableCell>{task.level}</TableCell>
                            <TableCell>
                                <ImageRender value={task.imagePath}/>
                            </TableCell>
                            <TableCell>{task.answer}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleToggleModal(task)} color={"yellow"} label={"Редагувати"} />
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <BasicModal isOpen={isOpen} onClose={closeModal} selectedTask={selectedTask} reloadData={reloadTableData} />
        </TableContainer>);
}

