import { GridColDef } from '@mui/x-data-grid';
export const columns: GridColDef[] = [
	{
		field: 'fullName',
		headerName: `Повне ім'я`,
		width: 350,
		headerClassName: 'super-app-theme--header',
	},
	{
		field: 'class',
		headerName: 'Клас',
		width: 150,
		headerClassName: 'super-app-theme--header',
	},
	{
		field: 'status',
		headerName: 'Статус',
		width: 150,
		headerClassName: 'super-app-theme--header',
	},
];