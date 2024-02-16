import {TaskDto} from "../../api/tasks/dto/tasks-response.dto.ts";
import {GridRowId} from "@mui/x-data-grid";
import {FC, useState} from "react";
import {Checkbox, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import BasicModal from "./modal.tsx";
import {Button} from '../../components/buttons/green-button.component.tsx';
import {api} from "../../core/api.ts";
import {useSelectedTopics, useStudents} from "../../context/data-provider.context.tsx";
import {useNavigate} from "react-router-dom";

interface TasksTableProps {
    tasks: TaskDto[];
    selectedTaskIds: TaskDto[];
    setSelectedTaskIds: (value: TaskDto[]) => void;
}

export const TasksTable: FC<TasksTableProps> = ({tasks, setSelectedTaskIds, selectedTaskIds}) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selectedTask, setSelectedTask] = useState<TaskDto | null>(null);
    const navigate = useNavigate();
    const {selectedStudentIds} = useStudents();
    const {selectedTopics} = useSelectedTopics();

    console.log("tasks: ", tasks);

    const handleToggleModal = (task: TaskDto) => {
        setSelectedTask(task);
        setIsOpen(true);
    };

    const closeModal = () => {
        setIsOpen(false);
        setSelectedTask(null);
    };

    const handleUnfinishedTasksSelection = (selectedRows: GridRowId[]) => {
        const selectedTasks = selectedRows.map((row) => {
            const taskId = Number(row);
            return tasks.find(task => task.id === taskId);
        }).filter(Boolean) as TaskDto[];

        // Перевірка, чи елемент вже вибраний
        const newSelectedTasks = selectedTaskIds.some(task => selectedTasks.some(selectedTask => selectedTask.id === task.id))
            ? selectedTaskIds.filter((task) => !selectedTasks.some(selectedTask => selectedTask.id === task.id))  // Видалення, якщо вже вибраний
            : [...selectedTaskIds, ...selectedTasks];  // Додавання, якщо ще не вибраний

        setSelectedTaskIds(newSelectedTasks);
    };

    const reloadTableData = async () => {
        try {
            const response = await api.post('/tasks/all', {
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

                    {tasks.map((task) => (
                        <TableRow
                            key={`unfinished-task-key-${task.id}`}
                            sx={{
                                '&:last-child td, &:last-child th':
                                    {border: 0},
                                cursor: 'pointer',
                                background: task.status == 'ASSIGNED' ? '#C0C0C0' : 'inherit'
                            }}
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
                                <img src={task.imagePath} alt="Image"/>
                            </TableCell>
                            <TableCell>{task.answer}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleToggleModal(task)} color={"green"} label={"Редагувати"}/>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <BasicModal isOpen={isOpen} onClose={closeModal} selectedTask={selectedTask} reloadData={reloadTableData}/>
        </TableContainer>);
}
