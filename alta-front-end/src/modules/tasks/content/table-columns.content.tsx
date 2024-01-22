import {GridColDef, } from "@mui/x-data-grid";
import {FC} from "react";
import ModalButton from "../modal/modal-button.tsx";


interface TaskContent {
    value: string;
}

// eslint-disable-next-line react-refresh/only-export-components
const ImageRender: FC<TaskContent> = ({value: link}) => {
    const imagePath = `${link}`;
    return <img src={imagePath} alt="Image"/>;
};


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
        renderCell: (params) => <ImageRender value={params.value as string}/>
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


