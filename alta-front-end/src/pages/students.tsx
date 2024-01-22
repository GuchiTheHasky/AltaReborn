import {GreenButton} from '../components/buttons/green-button.component';
import {DataGrid, GridRowParams} from "@mui/x-data-grid";
import {columns} from "../modules/students/content/table-columns.content.tsx";
import {useEffect, useState} from "react";
import {getStudents} from "../api/students/useGetStudents.ts";
import {StudentResponse} from "../api/students/dto/students-response.dto.ts";
import {useNavigate} from "react-router-dom";

export const Students = () => {

    const [students, setStudents] = useState<StudentResponse[]>([]);
    // eslint-disable-next-line prefer-const
    let [selectedStudent, setSelectedStudent] = useState<StudentResponse | undefined>();
    const [selectedRowId, setSelectedRowId] = useState<number | null>(null);
    const navigate = useNavigate();


    useEffect(() => {
        getStudents().then(setStudents);
    }, []);

    const handleNextButtonClick = () => {
        selectedStudent = students.find(student => student.id === selectedRowId);
        setSelectedStudent(selectedStudent);
        console.log("Вибрано: ", selectedStudent);
        if (selectedStudent) {
            localStorage.setItem("studentId", String(selectedStudent.id));
            navigate('/topics');
        }
    };


    const onRowClick = (params: GridRowParams) => {
        setSelectedRowId(params.id as number);
    };


    return (
        <div>
            <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
                <h2 className="text-green-primary w-[700px]">Студенти:</h2>


                <div className="h-[500px] w-[700px]">
                    <DataGrid
                        rows={students}
                        columns={columns}
                        initialState={{
                            pagination: {
                                paginationModel: {page: 0, pageSize: 10},
                            },
                        }}
                        pageSizeOptions={[5, 10]}
                        onRowClick={onRowClick}
                    />
                </div>


                <div className="w-[300px]">
                    <GreenButton label="ДАЛІ" onClick={handleNextButtonClick}/>
                </div>
            </div>
        </div>
    );
};
