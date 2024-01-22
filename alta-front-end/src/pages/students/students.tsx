import {Button} from '../../components/buttons/green-button.component.tsx';
import {useEffect, useState} from "react";
import {getStudents} from "../../api/students/useGetStudents.ts";
import {StudentResponse} from "../../api/students/dto/students-response.dto.ts";
import {Link} from "react-router-dom";
import {Typography} from '@mui/material';
import {StudentsTable} from './students-table.component.tsx';
import {useStudents} from "../../context/data-provider.context.tsx";

export const Students = () => {

    const [students, setStudents] = useState<StudentResponse[]>([]);
    const {selectedStudentIds, setSelectedStudentIds} = useStudents();


    useEffect(() => {
        getStudents().then(setStudents);
    }, []);


    return (
        <>

            <Typography variant="h4" component="h4" sx={{mb: 2}} className="text-green-primary">
                Студенти:
            </Typography>

            <StudentsTable students={students}
                           selectedStudentIds={selectedStudentIds}
                           setSelectedStudentIds={setSelectedStudentIds}
            />

            <div className={"flex justify-end pt-2"}>
                <Link to={'topics'}>
                    <Button className={'w-[300px] '} color={"green"} label="ДАЛІ"/>
                </Link>
            </div>
        </>
    );
};
