import React from 'react';
import {useNavigate} from 'react-router-dom';
import {useAppContext} from "../api/context/appContext.tsx";
import {StudentsTable} from '../modules/students/students-table.component';
import {GreenButton} from '../components/buttons/green-button.component';

export const Students: React.FC = () => {
    const {students, selectedStudent, setSelectedStudent} = useAppContext();
    const navigate = useNavigate();

    const handleButtonClick = () => {
        if (selectedStudent) {
            navigate('/topics');
        } else {
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
