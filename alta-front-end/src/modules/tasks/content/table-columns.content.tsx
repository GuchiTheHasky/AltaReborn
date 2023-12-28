import {GridColDef} from "@mui/x-data-grid";
import {FC} from "react";

interface TaskContent {
    value: string;
}

// eslint-disable-next-line react-refresh/only-export-components
const ImageRender: FC<TaskContent> = ({value: link}) => {
    const imagePath = `${link}`;
    return <img src={imagePath} alt="Image"/>;
};

const HtmlRenderer: FC<TaskContent> = ({value: htmlContent}) => {
    return <div dangerouslySetInnerHTML={{__html: htmlContent}}/>;
};

export const columns: GridColDef[] = [
    {
        field: 'title',
        headerName: 'Тема',
        width: 200,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'level',
        headerName: 'Рівень',
        width: 75,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'textHtml',
        headerName: 'Текст HTML',
        width: 400,
        headerClassName: 'super-app-theme--header',
        renderCell: (params) => <HtmlRenderer value={params.value as string}/>,
    },
    {
        field: 'answer',
        headerName: 'Відповідь',
        width: 100,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'imagePath',
        headerName: 'Зображення',
        width: 550,
        headerClassName: 'super-app-theme--header',
        renderCell: (params) => <ImageRender value={params.value as string}/>
    }
];
