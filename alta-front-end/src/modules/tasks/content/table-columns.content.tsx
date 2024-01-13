import {GridColDef, } from "@mui/x-data-grid";
import {FC} from "react";
import {ModalButton} from "../modal/modal-button.tsx";
import {Entity} from "../modal/entity.ts";


interface TaskContent {
    value: string;
}

interface RowID {
    id: number;
}

// eslint-disable-next-line react-refresh/only-export-components
const ImageRender: FC<TaskContent> = ({value: link}) => {
    const imagePath = `${link}`;
    return <img src={imagePath} alt="Image"/>;
};

// const HtmlRenderer: FC<TaskContent> = ({value: htmlContent}) => {   // todo: (textHtml)
//     return <div dangerouslySetInnerHTML={{__html: htmlContent}}/>;
// };


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
    // {
    //     field: 'textHtml', // todo: (textHtml) img link is not available & have a redundant characters: '\'.
    //     headerName: 'Текст HTML',
    //     width: 400,
    //     headerClassName: 'super-app-theme--header',
    //     renderCell: (params) => <HtmlRenderer value={params.value as string}/>,
    //     align: 'center',
    // },
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
        renderCell:
            (params) => <ModalButton entity={params.row as Entity}  id={params.row.id}/>
    },
];
