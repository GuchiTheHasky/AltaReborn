import {Button} from "../../components/buttons/green-button.component.tsx";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {useStudents} from "../../context/data-provider.context.tsx";
import {TopicsTable} from "./topics-table.component.tsx";
import {TopicResponse} from "../../api/topics/dto/topics-response.dto.ts";
import {getTopics} from "../../api/topics/useGetTopics.ts";
import { api } from "../../core/api.ts";

export const Topics = () => {
    const [selectedRows, setSelectedRows] = useState<number[]>([]);
    const [topics, setTopics] = useState<TopicResponse[]>([])
    const {selectedStudentIds} = useStudents();
    const navigate = useNavigate();
    const [ topics, setTopics] = useState<TopicResponse[]>([]);

    useEffect(() => {
        getTopics().then(setTopics);
    }, []);

    const sendToBackend = async (selectedRows: number[] | undefined, selectedStudentId: number[] | null) => {
        const topicsIds = selectedRows?.join(',');
        console.log('topicsIds: ', topicsIds);
        console.log('selectedStudentId: ', selectedStudentId);
        console.log('selectedRows: ', selectedRows);

        try {
            const response = await api.post('/tasks/unfinished', {
                topics: selectedRows,
                students: selectedStudentId,
            }, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            const tasks = response.data;
            navigate('/tasks', { state: { tasks } });
            console.log('response tasks: ', tasks);
        } catch (error) {
            console.error('Error sending data to backend:', error);
        }
    };

    const handleNextButtonClick = () => {
        console.log("Вибрані id тем:", selectedRows);
        console.log("Вибраний студент:", selectedStudentIds);
         sendToBackend(selectedRows, selectedStudentIds);
    };

    return (
        <>
            <h2 className="text-green-primary">Теми:</h2>

            <div className="min-h-[500px]">
                <TopicsTable setSelectedTopicIds={setSelectedRows} selectedTopicIds={selectedRows} topics={topics}/>
            </div>

            <div className={'flex justify-around mt-4 gap-5'}>
                <Button className={'flex-1 '} color={"yellow"} label="НАЗАД" onClick={() => window.history.back()}/>

                <Button className={'flex-1 '} color={"green"} label="ДАЛІ" onClick={handleNextButtonClick}/>
            </div>
        </>
    );
};
