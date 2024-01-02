import { TasksTable } from '../modules/tasks/tasks-table.component';
import { useLocation } from 'react-router-dom';
import {GreenButton} from "../components/buttons/green-button.component.tsx";
import {TasksResponse} from "../api/tasks/dto/tasks-response.dto.ts";
import {useState} from "react";
import {api} from "../core/api.ts";


export const Tasks = () => {
    const location = useLocation();
    const tasks = location.state?.tasks || [];

    const [selectedTask, setSelectedTask] = useState<TasksResponse[]>();

    const handleAnswers = () => {
        answers(selectedTask);
    };

    const handleNoAnswers = () => {

    };

    const answers = async (tasks: TasksResponse[] | undefined) => {
        const tasksIds = tasks?.map(task => task.id).join(',');
        const response = await api.get('/tasks/answers', {
            params: {
                tasks: tasksIds,
            },
            headers: {
                'Content-Type': 'application/json',
            },
        });
        const blob = new Blob([response.data], { type: 'text/html' });
        const url = URL.createObjectURL(blob);

        window.open(url, '_blank');
    };


    return (
        <div>
            <div className="flex gap-2.5">
                <div className="w-[300px]">
                    <GreenButton label="З ВІДПОВІДЯМИ" onClick={handleAnswers}/>
                </div>
                <div className="w-[300px]" onClick={handleNoAnswers}>
                    <GreenButton label="БЕЗ ВІДПОВІДЕЙ"/>
                </div>
            </div>

            <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
                <h2 className="text-green-primary w-[700px]">Завдання:</h2>
                <TasksTable tasks={tasks} onSelectTask={setSelectedTask}/>
            </div>

        </div>
    );
};
