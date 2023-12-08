import { GreenButton } from '../components/buttons/green-button.component';
import { StudentsTable } from '../modules/students/students-table.component';

export const Students = () => {
	return (
		<div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
			<StudentsTable />
			<div className='w-[300px]' >
				<GreenButton label="ДАЛІ" />
			</div>
		</div>
	);
};
