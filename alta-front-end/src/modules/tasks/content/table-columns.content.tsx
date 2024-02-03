import {GridColDef, } from "@mui/x-data-grid";
import ModalButton from "../modal/modal-button.tsx";

export const columns: GridColDef[] = [
    {
        field: 'title',
        headerName: 'Тема',
        width: 300,
        headerClassName: 'super-app-theme--header',
        align: 'center',
    },
    {
        field: 'level',
        headerName: 'Рівень',
        width: 75,
        headerClassName: 'super-app-theme--header',
        align: 'center',
    },
    {
        field: 'imagePath',
        headerName: 'Завдання',
        width: 550,
        headerClassName: 'super-app-theme--header',
        align: 'center',
    },
    {
        field: 'answer',
        headerName: 'Відповідь',
        width: 100,
        headerClassName: 'super-app-theme--header',
        align: 'center',
    },
    {
        field: 'edit',
        headerName: 'Редагувати',
        width: 100,
        headerClassName: 'super-app-theme--header',
        align: 'center',
        renderCell: (params) => <ModalButton currentTask={params.row} />
    },
];


