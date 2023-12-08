import { DataGrid } from '@mui/x-data-grid';
import { students } from './mocks/students.mock';
import {columns} from './content/table-columns.content';


export const StudentsTable = () => {
	return (
		<div className="h-[500px] w-[700px]">
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
				
			/>
		</div>
	);
};
