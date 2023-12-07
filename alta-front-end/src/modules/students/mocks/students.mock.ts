import { StudentResponse, StudentStatus } from '../../../api/students/dto/students.response.dto';

const studentsResponse: StudentResponse[] = [
	{ id: 1, lastName: 'Snow', firstName: 'Jon', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 2, lastName: 'Lannister', firstName: 'Cersei', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 3, lastName: 'Lannister', firstName: 'Jaime', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 4, lastName: 'Stark', firstName: 'Arya', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 5, lastName: 'Targaryen', firstName: 'Daenerys', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 6, lastName: 'Melisandre', firstName: 'Hen', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 7, lastName: 'Clifford', firstName: 'Ferrara', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 8, lastName: 'Frances', firstName: 'Rossini', class: '11-B', status: StudentStatus.REMOTE },
	{ id: 9, lastName: 'Roxie', firstName: 'Harvey', class: '11-B', status: StudentStatus.REMOTE },
];

const createStudentFullName = (studentsResponse: StudentResponse[]) => {
	return studentsResponse.map(student => {
		return {
			...student,
			fullName: `${student.firstName} ${student.lastName}`,
		};
	});
};

export const students = createStudentFullName(studentsResponse);
