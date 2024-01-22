import { useLocation } from 'react-router-dom';
import {Button} from "../../components/buttons/green-button.component.tsx";
import {TasksResponse} from "../../api/tasks/dto/tasks-response.dto.ts";
import {useEffect, useState} from "react";
import {YellowButton} from "../../components/buttons/yellow-button.component.tsx";
import {DataGrid} from "@mui/x-data-grid";
import {columns} from "../../modules/tasks/content/table-columns.content.tsx";
import {api} from "../../core/api.ts";

export const Tasks = () => {
    const location = useLocation();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    const tasks = location.state?.tasks || [];
    const selectedStudentId = localStorage.getItem("studentId");

    const [loadedTasks, setLoadedTasks] = useState<TasksResponse[]>([]);
    const [selectedRows, setSelectedRows] = useState<number[]>([]);

    useEffect(() => { setLoadedTasks(tasks) }, [tasks]);



    const answers = async (selectedRows: number[] | undefined, selectedStudentId: string | null) => {
        const tasksIds = selectedRows?.map(task => task).join(',');
        const response = await api.get('/tasks/answers', {
            params: {
                tasks: tasksIds,
                student: selectedStudentId
            },
            headers: {
                'Content-Type': 'application/json',
            },
        });
        const blob = new Blob([response.data], { type: 'text/html' });
        const url = URL.createObjectURL(blob);

        window.open(url, '_blank');
    };

        const handleAnswers = () => {
        answers(selectedRows, selectedStudentId);
    };


    return (
        <div>
            <div className="flex gap-2.5">
                <div>
                    <YellowButton label="НАЗАД" onClick={() => window.history.back()}/>
                </div>
                <div className="w-[300px]">
                    <Button label="З ВІДПОВІДЯМИ" onClick={handleAnswers}/>
                </div>
                <div className="w-[300px]" onClick={() => {}}>
                    <Button label="БЕЗ ВІДПОВІДЕЙ"/>
                </div>
            </div>

            <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
                <h2 className="text-green-primary w-[700px]">Завдання:</h2>

                <div style={{height: '500px', width: '1200px'}}>
                    <DataGrid
                        rows={loadedTasks}
                        columns={columns.map(column => ({
                            ...column,
                            headerAlign: 'center',
                        }))}

                        initialState={{
                            pagination: {
                                paginationModel: {page: 0, pageSize: 10},
                            },
                        }}
                        pageSizeOptions={[5, 10, 20, 50, 100]}
                        checkboxSelection
                        getRowHeight={() => 'auto'}
                        getEstimatedRowHeight={() => 200}
                        rowSelectionModel={selectedRows}
                        onRowSelectionModelChange={(newSelection) => setSelectedRows(newSelection as number[])}
                    />

                </div>
            </div>

        </div>
    );
};

