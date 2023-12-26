import { DataGrid } from '@mui/x-data-grid';
import { columns } from './content/table-columns.content'; // Імпорт конфігурації стовпців таблиці
import { useEffect, useState, FC } from 'react';
import { StudentResponse } from '../../api/students/dto/students-response.dto'; // Імпорт типу даних студента

// Інтерфейс для властивостей компонента StudentsTable
interface StudentsTableProps {
	students: StudentResponse[] | undefined;
}

export const StudentsTable: FC<StudentsTableProps> = ({ students }) => {
	// Стан для зберігання завантажених студентів для відображення у таблиці
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
		// Перевірка чи передано значення для students
		if (students) {
			// Оновлення стану loadedStudents з переданими students
			setLoadedStudents(students);
		}
	}, [students]);

	// Повертає JSX елемент, який містить таблицю студентів
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
