import {GridColDef} from '@mui/x-data-grid';
import {FC} from "react";

interface Student {
    firstName?: string;
    lastName?: string;
}

// eslint-disable-next-line react-refresh/only-export-components
const FullNameRender: FC<Student> = ({firstName, lastName}) => {
    const fullName = `${firstName || ''} ${lastName || ''}`;
    return <div>{fullName}</div>;
};

export const columns: GridColDef[] = [

    {
        field: 'fullName',
        headerName: `Повне ім'я`,
        width: 350,
        headerClassName: 'super-app-theme--header',
        renderCell: (params) => (
            <FullNameRender firstName={params.row.firstName} lastName={params.row.lastName}/>
        ),
    },
    {
        field: 'grade',
        headerName: 'Клас',
        width: 150,
        headerClassName: 'super-app-theme--header',
    },
    {
        field: 'comment',
        headerName: 'Коментар',
        width: 150,
        headerClassName: 'super-app-theme--header',
    },
];