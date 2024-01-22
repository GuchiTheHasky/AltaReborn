import { GridColDef } from '@mui/x-data-grid';

export const columns: GridColDef[] = [

    {
        field: 'fullName',
        headerName: `Повне ім'я`,
        width: 350,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'grade',
        headerName: 'Клас',
        width: 150,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'comment',
        headerName: 'Статус',
        width: 150,
        headerClassName: 'super-app-theme--header',
    },
];
