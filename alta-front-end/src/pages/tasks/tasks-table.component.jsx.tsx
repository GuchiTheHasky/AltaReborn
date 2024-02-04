import {TaskDto, TasksResponse} from "../../api/tasks/dto/tasks-response.dto.ts";
import {GridRowId} from "@mui/x-data-grid";
import {FC, useState} from "react";
import {Checkbox, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import {ImageRender} from "./image.tsx";
import BasicModal from "./modal.tsx";
import Button from "@mui/material/Button";

interface TasksTableProps {
    tasks: TasksResponse;
    selectedTaskIds: TaskDto[];
    setSelectedTaskIds: (value: TaskDto[]) => void;
}

export const TasksTable: FC<TasksTableProps> = ({tasks, setSelectedTaskIds, selectedTaskIds}) => {
    const [isOpen, setIsOpen] = useState(false);


    const handleToggleModal = (task: TaskDto) => {

        setIsOpen(!isOpen);
    };
    // const handleRowSelection = (selectedRows: GridRowId[]) => {
    //     const selectedIds = selectedRows.map((row) => Number(row));
    //
    //     // Перевірка, чи елемент вже вибраний
    //     const newSelectedIds = selectedTaskIds.includes(selectedIds[0])
    //         ? selectedTaskIds.filter((id) => id !== selectedIds[0])  // Видалення, якщо вже вибраний
    //         : [...selectedTaskIds, ...selectedIds];  // Додавання, якщо ще не вибраний
    //
    //     setSelectedTaskIds(newSelectedIds);
    // };
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
                                    // color="primary"
                                    // checked={selectedTaskIds.indexOf(task.id) !== -1}
                                    // onClick={() => handleRowSelection([task.id])}
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
                                <Button onClick={() => handleToggleModal(task)}>Edit</Button>
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
                                    // color="primary"
                                    // checked={selectedTaskIds.indexOf(task.id) !== -1}
                                    // onClick={() => handleRowSelection([task.id])}
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
                                <Button onClick={() => handleToggleModal(task)}>Edit</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <BasicModal isOpen={isOpen} onClose={handleToggleModal} />
        </TableContainer>);

}

