import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { students } from './mocks/students.mock';

const columns: GridColDef[] = [
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

export const StudentsTable = () => {
	return (
		<div className="h-[600px] w-[700px]">
			<DataGrid
				rows={students}
				columns={columns}
				initialState={{
					pagination: {
						paginationModel: { page: 0, pageSize: 10 },
					},
				}}
				pageSizeOptions={[5, 10]}
				checkboxSelection
				sx={{
					'& .super-app-theme--header': {
						backgroundColor: '#79aa2d',
						color: 'white',
					},
				}}
				
			/>
		</div>
	);
};
