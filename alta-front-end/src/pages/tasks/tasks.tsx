import {useLocation} from 'react-router-dom';
import {Button} from "../../components/buttons/green-button.component.tsx";
import {TaskDto} from "../../api/tasks/dto/tasks-response.dto.ts";
import {useState} from "react";
import {api} from "../../core/api.ts";
import {TasksTable} from "./tasks-table.component.jsx.tsx";
import {StudentDto} from "../../api/students/dto/students-response.dto.ts";
import {useStudents} from "../../context/data-provider.context.tsx";

export const Tasks = () => {
    const location = useLocation();
    const tasks = location.state?.tasks || [];
    const {selectedStudentIds} = useStudents();
    const [selectedRows, setSelectedRows] = useState<TaskDto[]>([]);

    const answers = async (selectedRows: TaskDto[] | undefined, selectedStudentIds: StudentDto[] | null) => {
        const tasksIds = selectedRows?.map(task => task);

        const response = await api.post('/tasks/answers', {
            tasks: tasksIds,
            student: selectedStudentIds,
        });
        const blob = new Blob([response.data], {type: 'text/html'});
        const url = URL.createObjectURL(blob);

        window.open(url, '_blank');
    };

    const handleAnswers = () => {
        answers(selectedRows, selectedStudentIds);
    };

    const withoutAnswers = async (selectedRows: TaskDto[] | undefined, selectedStudentIds: StudentDto[] | null) => {
        const tasksIds = selectedRows?.map(task => task);

        const response = await api.post('/tasks/noAnswers', {
            tasks: tasksIds,
            student: selectedStudentIds,
        });
        const blob = new Blob([response.data], {type: 'text/html'});
        const url = URL.createObjectURL(blob);

        window.open(url, '_blank');
    };

    const handleWithoutAnswers = () => {
        withoutAnswers(selectedRows, selectedStudentIds);
    };

    return (
        <div>
            <div className={'flex justify-around mt-2 gap-3'}>
                <Button className={'flex-1 '} color={"yellow"} label={'НАЗАД'} onClick={() => window.history.back()}/>

                <Button className={'flex-1 '} color={"green"} label={'ЗАВДАННЯ З ВІДПОВІДЯМИ'} onClick={handleAnswers}/>

                <Button className={'flex-1 '} color={"green"} label={'ЗАВДАННЯ БЕЗ ВІДПОВІДЕЙ'} onClick={handleWithoutAnswers}/>
            </div>

            <div className="flex justify-around mt-2 gap-3">
                <TasksTable tasks={tasks}
                            selectedTaskIds={selectedRows}
                            setSelectedTaskIds={setSelectedRows}/>
            </div>
        </div>
    );
};

