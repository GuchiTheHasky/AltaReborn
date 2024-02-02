import {TasksResponse} from "../../api/tasks/dto/tasks-response.dto.ts";
import {GridRowId} from "@mui/x-data-grid";
import {FC} from "react";
import {Checkbox, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

interface TasksTableProps {
    tasks: TasksResponse;
    selectedTaskIds: number[];
    setSelectedTaskIds: (value: number[]) => void;
}


export const TasksTable: FC<TasksTableProps> = ({tasks, setSelectedTaskIds, selectedTaskIds}) => {
    console.log('Tasks: ', tasks);
    console.log("here: ", tasks instanceof Array);
    console.log("hetre: ", tasks instanceof Object);

    const handleRowSelection = (selectedRows: GridRowId[]) => {
        const selectedIds = selectedRows.map((row) => Number(row));
        setSelectedTaskIds(selectedIds);
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
                                    checked={selectedTaskIds.indexOf(task.id) !== -1}
                                    onClick={(event) => handleRowSelection([task.id])}
                                />
                            </TableCell>
                            <TableCell component="th" scope="row">
                                {task.title}
                            </TableCell>
                            <TableCell>{task.level}</TableCell>
                            <TableCell>{task.imagePath}</TableCell>
                            <TableCell>{task.answer}</TableCell>
                            <TableCell>Редагувати</TableCell>
                        </TableRow>
                    ))}
                    {tasks.tasksCompletedByAtLeastOneStudent.map((task) => (
                        <TableRow
                            key={`completed-task-key-${task.id}`}
                            sx={{'&:last-child td, &:last-child th': {border: 0}, cursor: 'pointer'}}
                        >
                            <TableCell padding="checkbox">
                                <Checkbox
                                    // color="primary"
                                    checked={selectedTaskIds.indexOf(task.id) !== -1}
                                    onClick={(event) => handleRowSelection([task.id])}
                                />
                            </TableCell>
                            <TableCell component="th" scope="row">
                                {task.title}
                            </TableCell>
                            <TableCell>{task.level}</TableCell>
                            <TableCell>{task.imagePath}</TableCell>
                            <TableCell>{task.answer}</TableCell>
                            <TableCell>Редагувати</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>);

}

