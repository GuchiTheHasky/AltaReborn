import {GridColDef} from "@mui/x-data-grid";

export const columns: GridColDef[] = [
    {
        field: 'title',
        headerName: 'Тема',
        width: 250,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'level',
        headerName: 'Рівень',
        width: 150,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'grade',
        headerName: 'Клас',
        width: 100,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'text',
        headerName: 'Текст',
        width: 250,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'textHtml',
        headerName: 'Текст HTML',
        width: 250,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'answer',
        headerName: 'Відповідь',
        width: 100,
        headerClassName: 'super-app-theme--header',
    }
];
