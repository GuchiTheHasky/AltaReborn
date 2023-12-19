import { GreenButton } from '../components/buttons/green-button.component';
import { StudentsTable } from '../modules/students/students-table.component';
import { useGetStudents } from '../api/students/useGetStudents';
import { Backdrop, CircularProgress } from '@mui/material';

export const Students = () => {
	const { data: students, isLoading } = useGetStudents();

	return (
		<div>
			{isLoading && (
				<Backdrop open={isLoading}>
					<CircularProgress color="inherit" />
				</Backdrop>
			)}
			<div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
				<h2 className="text-green-primary w-[700px]">Студенти:</h2>
				<StudentsTable students={students} />
				<div className="w-[300px]">
					<GreenButton label="ДАЛІ" />
				</div>
			</div>
		</div>
	);
};
