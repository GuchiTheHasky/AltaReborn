// import {DataGrid, GridRowId} from "@mui/x-data-grid";
// import React, {FC, useEffect, useState} from "react";
// import { TasksResponse } from "../../api/tasks/dto/tasks-response.dto";
// import { columns } from "./content/table-columns.content";
//
//
// interface TasksTableProps {
//   tasks: TasksResponse[] | undefined;
//   onSelectTask: React.Dispatch<React.SetStateAction<TasksResponse | TasksResponse[] | null>>;
// }
//
// export const TasksTable: FC<TasksTableProps> = ({ tasks, onSelectTask }) => {
//   const [loadedTasks, setLoadedTasks] = useState<TasksResponse[]>([
//     {
//       id: 1,
//       title: "",
//       imagePath: "",
//       level: "",
//       answer: "",
//     },
//   ]);
//
//   const [selectionTask, setSelectionTask] = useState<GridRowId[]>([]);
//
//   useEffect(() => {
//     if (tasks) {
//       setLoadedTasks(tasks);
//     }
//   }, [tasks]);
//
//   const handleRowSelection = (selectedRows: GridRowId[]) => {
//       setSelectionTask(selectedRows);
//
//         const selectedTasks = loadedTasks.filter(task => selectedRows.includes(task.id));
//         onSelectTask(selectedTasks);
//   };
//
//
//     return (
//         <div>
//       <div style={{height: '500px', width: '1200px'}}>
//         <DataGrid
//             rows={loadedTasks}
//             columns={columns.map(column => ({
//                 ...column,
//                 headerAlign: 'center',
//             }))}
//
//             initialState={{
//                 pagination: {
//                     paginationModel: { page: 0, pageSize: 10 },
//                 },
//             }}
//             pageSizeOptions={[5, 10, 20, 50, 100]}
//             checkboxSelection
//             getRowHeight={() => 'auto'}
//             getEstimatedRowHeight={() => 200}
//             rowSelectionModel={selectionTask}
//             onRowSelectionModelChange={handleRowSelection}
//         />
//       </div>
//
// </div>
//   );
// }