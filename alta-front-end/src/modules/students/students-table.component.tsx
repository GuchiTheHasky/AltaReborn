// import {DataGrid} from '@mui/x-data-grid';
// import {columns} from './content/table-columns.content';
// import {getStudents} from "../../api/students/useGetStudents.ts";
// import {useEffect, useState} from "react";
//
//
//
// export const StudentsTable = () => {
//     const [students, setStudents] = useState([]);
//     const [selectedStudent, setSelectedStudent] = useState([]);
//
//
//     useEffect(() => {
//         getStudents().then(setStudents);
//     }, []);
//
//     const handleRowSelection = (selectedRows) => {
//         if (selectedRows.length > 0) {
//             const selectedStudent = students[selectedRows[0].data.id];
//             setSelectedStudent(selectedStudent);
//         }
//     };
//
//
//     return (
//         <div className="h-[500px] w-[700px]">
//             <DataGrid
//                 rows={students}
//                 columns={columns}
//                 initialState={{
//                     pagination: {
//                         paginationModel: {page: 0, pageSize: 10},
//                     },
//                 }}
//                 pageSizeOptions={[5, 10]}
//                 onSelectionModelChange={handleRowSelection}
//             />
//         </div>
//     );
// };
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// // import { DataGrid, GridRowId } from '@mui/x-data-grid';
// // import { columns } from './content/table-columns.content.tsx';
// // import { useState, FC } from 'react';
// // import { useAppContext } from "../../api/context/appContext.tsx";
// // import { StudentResponse } from "../../api/students/dto/students-response.dto.ts";
// //
// // interface StudentsTableProps {
// //     onSelectStudent: (selectedStudent: StudentResponse) => void;
// // }
// //
// // export const StudentsTable: FC<StudentsTableProps> = ({ onSelectStudent }) => {
// //     const { students } = useAppContext();
// //
// //     const [selectionRow, setSelectionRow] = useState<GridRowId[]>([]);
// //
// //     const handleRowSelection = (selectedRows: GridRowId[]) => {
// //         setSelectionRow(selectedRows);
// //
// //         if (selectedRows.length > 0) {
// //             const selectedStudent = students[selectedRows[0] as number];
// //
// //             if (selectedStudent) {
// //                 onSelectStudent(selectedStudent);
// //             } else {
// //                 console.error('Selected student is undefined or null');
// //             }
// //         }
// //     };
// //
// //     return (
// //         <div className="h-[500px] w-[700px]">
// //             <DataGrid
// //                 rows={Object.values(students)}
// //                 columns={columns}
// //                 initialState={{
// //                     pagination: {
// //                         paginationModel: { page: 0, pageSize: 10 },
// //                     },
// //                 }}
// //                 sx={{
// //                     '& .MuiDataGrid-columnHeaderCheckbox .MuiDataGrid-columnHeaderTitleContainer': {
// //                         display: 'none',
// //                     },
// //                 }}
// //                 pageSizeOptions={[5, 10]}
// //                 rowSelectionModel={selectionRow}
// //                 onRowSelectionModelChange={handleRowSelection}
// //             />
// //         </div>
// //     );
// // };
//
