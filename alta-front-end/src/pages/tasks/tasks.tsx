import {useLocation} from 'react-router-dom';
import {Button} from "../../components/buttons/green-button.component.tsx";
import {TasksResponse} from "../../api/tasks/dto/tasks-response.dto.ts";
import {useEffect, useState} from "react";
import {api} from "../../core/api.ts";
import {TasksTable} from "./tasks-table.component.jsx.tsx";

export const Tasks = () => {
    const location = useLocation();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    const tasks = location.state?.tasks || [];
    const selectedStudentId = localStorage.getItem("studentId");
    console.log("tasks: ", tasks);

    const [loadedTasks, setLoadedTasks] = useState<TasksResponse[]>([]);
    const [selectedRows, setSelectedRows] = useState<number[]>([]);

    useEffect(() => {
        setLoadedTasks([tasks])
    }, [tasks]);


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
        const blob = new Blob([response.data], {type: 'text/html'});
        const url = URL.createObjectURL(blob);

        window.open(url, '_blank');
    };

    const handleAnswers = () => {
        answers(selectedRows, selectedStudentId);
    };


    return (
        <div>
            <div className={'flex justify-around mt-2 gap-3'}>
                <Button className={'flex-1 '} color={"yellow"} label={'НАЗАД'} onClick={() => window.history.back()}/>

                <Button className={'flex-1 '} color={"green"} label={'З ВІДПОВІДЯМИ'} onClick={handleAnswers}/>

                <Button className={'flex-1 '} color={"green"} label={'БЕЗ ВІДПОВІДЕЙ'}/>
            </div>

            <div className="flex justify-around mt-2 gap-3">
                <TasksTable tasks={tasks}
                            selectedTaskIds={selectedRows}
                            setSelectedTaskIds={setSelectedRows}/>
            </div>
        </div>
    );
};

