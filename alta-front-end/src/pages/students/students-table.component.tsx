import {StudentDto} from "../../api/students/dto/students-response.dto.ts";
import React, {FC} from "react";
import {Checkbox, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

interface StudentsTableProps {
    students: StudentDto[];
    selectedStudentIds: StudentDto[];
    setSelectedStudentIds: (value: StudentDto[]) => void;

}

export const StudentsTable: FC<StudentsTableProps> = ({students, setSelectedStudentIds, selectedStudentIds}) => {

    const handleSelectAllClick = (event: React.ChangeEvent<HTMLInputElement>) => {
        // if (event.target.checked) {
        //     const newSelectedIds = students.map((student) => student.id);
        //     setSelectedStudentIds(newSelectedIds);
        //     return;
        // }
        // setSelectedStudentIds([]);
        if (event.target.checked) {
            setSelectedStudentIds(students);
            return;
        }
        setSelectedStudentIds([]);
    };

    // const handleClick = (_: any, id: number) => {
    //     const selectedIndex = selectedStudentIds.indexOf(id);
    //     let newSelected: number[] = [];
    //
    //     if (selectedIndex === -1) {
    //         newSelected = newSelected.concat(selectedStudentIds, id);
    //     } else if (selectedIndex === 0) {
    //         newSelected = newSelected.concat(selectedStudentIds.slice(1));
    //     } else if (selectedIndex === selectedStudentIds.length - 1) {
    //         newSelected = newSelected.concat(selectedStudentIds.slice(0, -1));
    //     } else if (selectedIndex > 0) {
    //         newSelected = newSelected.concat(
    //             selectedStudentIds.slice(0, selectedIndex),
    //             selectedStudentIds.slice(selectedIndex + 1),
    //         );
    //     }
    //     localStorage.setItem("selectedStudentIds", JSON.stringify(newSelected));
    //
    //     setSelectedStudentIds(newSelected);
    // };
    const handleClick = (_event: React.MouseEvent<HTMLTableRowElement>, clickedStudent: StudentDto) => {
        const selectedIndex = selectedStudentIds.findIndex((s) => s.id === clickedStudent.id);
        let newSelected: StudentDto[] = [];

        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selectedStudentIds, clickedStudent);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selectedStudentIds.slice(1));
        } else if (selectedIndex === selectedStudentIds.length - 1) {
            newSelected = newSelected.concat(selectedStudentIds.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                selectedStudentIds.slice(0, selectedIndex),
                selectedStudentIds.slice(selectedIndex + 1),
            );
        }
        localStorage.setItem("selectedStudentIds", JSON.stringify(newSelected));

        setSelectedStudentIds(newSelected);
    };

    // const isSelected = (id: number) => selectedStudentIds.indexOf(id) !== -1;
    const isSelected = (student: StudentDto) => selectedStudentIds.some((s) => s.id === student.id);

    const rowCount = students.length;
    const numSelected = selectedStudentIds.length;

    return <TableContainer>
        <Table sx={{minWidth: 650}} aria-label="simple table">
            <TableHead>
                <TableRow>
                    <TableCell padding="checkbox">
                        <Checkbox
                            color="primary"
                            indeterminate={numSelected > 0 && numSelected < rowCount}
                            checked={rowCount > 0 && numSelected === rowCount}
                            onChange={handleSelectAllClick}
                            inputProps={{
                                'aria-label': 'select all desserts',
                            }}
                        />
                    </TableCell>
                    <TableCell>Ім'я</TableCell>
                    <TableCell align="right">Клас</TableCell>
                    <TableCell align="right">Коментар</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {students.map((student) => (
                    <TableRow
                        key={'student-key' + student.id}
                        sx={{'&:last-child td, &:last-child th': {border: 0}, cursor: 'pointer'}}
                        hover
                        // onClick={(event) => handleClick(event, student.id)}
                        onClick={(event) => handleClick(event, student)}
                        role="checkbox"
                        selected={isSelected(student)}
                    >
                        <TableCell padding="checkbox">
                            <Checkbox
                                color="primary"
                                checked={isSelected(student)}
                            />
                        </TableCell>
                        <TableCell component="th" scope="row">
                            {student.fullName}
                        </TableCell>
                        <TableCell align="right">{student.grade}</TableCell>
                        <TableCell align="right">{student.comment}</TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    </TableContainer>
}