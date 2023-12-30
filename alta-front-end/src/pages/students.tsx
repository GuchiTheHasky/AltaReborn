import React from 'react';
import {useNavigate} from 'react-router-dom';
import {useAppContext} from "../api/context/appContext.tsx";
import {StudentsTable} from '../modules/students/students-table.component';
import {GreenButton} from '../components/buttons/green-button.component';

export const Students: React.FC = () => {
    const {students, selectedStudent, setSelectedStudent} = useAppContext();
    const navigate = useNavigate();

    const handleButtonClick = () => {
        console.log('Selected Student:', selectedStudent);
        if (selectedStudent) {
            // Встановлюємо вибраного студента в контексті
            //setSelectedStudent(selectedStudent);

            // Переходимо на іншу сторінку
            navigate('/topics');
        } else {
            // Виводимо повідомлення, якщо студент не обраний
            console.warn('Будь ласка, виберіть студента перед переходом.');
        }
    };

    return (
        <div>
            <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
                <h2 className="text-green-primary w-[700px]">Студенти:</h2>
                <StudentsTable students={students} onSelectStudent={setSelectedStudent}/>
                <div className="w-[300px]">
                    <GreenButton label="ДАЛІ" onClick={handleButtonClick}/>
                </div>
            </div>
        </div>
    );
};


// import { GreenButton } from '../components/buttons/green-button.component';
// import { StudentsTable } from '../modules/students/students-table.component';
// // import { useGetStudents } from '../api/students/useGetStudents';
//  import { Backdrop, CircularProgress } from '@mui/material';
// import { useNavigate } from 'react-router-dom';
// import { useAppContext } from "../api/context/appContext.tsx";
// // import {StudentResponse} from "../api/students/dto/students-response.dto.ts";
// // import {useState} from "react";
//
//
// export const Students: React.FC = () => {
//
// 	// const { data: students, isLoading } = useGetStudents();
// 	// const navigate = useNavigate();
// 	// //const [selectedStudent, setSelectedStudent] = useState<StudentResponse | null>(null);
// 	// const { setSelectedStudent } = useAppContext();
//
// 	const { data: students, isLoading, selectedStudent, setSelectedStudent } = useAppContext();
// 	const navigate = useNavigate();
// 	const handleButtonClick = () => {
// 		if (selectedStudent) {
// 			setSelectedStudent(selectedStudent);
// 			// Якщо студент обраний, переходимо на іншу сторінку
// 			navigate('/topics');
// 		} else {
// 			// Якщо студент не обраний, можна вивести повідомлення або залишити на поточній сторінці
// 			console.warn('Будь ласка, виберіть студента перед переходом.');
// 		}
// 	};
//
// 	return (
// 		<div>
// 			{isLoading && (
// 				<Backdrop open={isLoading}>
// 					<CircularProgress color="inherit" />
// 				</Backdrop>
// 			)}
// 			<div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
// 				<h2 className="text-green-primary w-[700px]">Студенти:</h2>
// 				<StudentsTable students={students} onSelectStudent={setSelectedStudent} />
// 				<div className="w-[300px]">
// 					<GreenButton label="ДАЛІ" onClick={handleButtonClick} />
// 				</div>
// 			</div>
// 		</div>
// 	);
// };
