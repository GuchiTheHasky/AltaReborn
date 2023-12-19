import { DataGrid } from '@mui/x-data-grid';
import { columns } from './content/table-columns.content';
import { useEffect, useState, FC } from 'react';
import { StudentResponse } from '../../api/students/dto/students-response.dto';

interface StudentsTableProps {
	students: StudentResponse[] | undefined;
}

export const StudentsTable: FC<StudentsTableProps> = ({ students }) => {
	const [loadedStudents, setLoadedStudents] = useState<StudentResponse[]>([
		{
			id: 1,
			firstName: '',
			lastName: '',
			grade: '',
			comment: '',
		},
	]);

	useEffect(() => {
		if (students) {
			setLoadedStudents(students);
		}
	}, [students]);

	return (
		<div className="h-[500px] w-[700px]">
			<DataGrid
				rows={loadedStudents}
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
