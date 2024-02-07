import {Button} from "../../components/buttons/green-button.component.tsx";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {useSelectedTopics, useStudents, useTitles} from "../../context/data-provider.context.tsx";
import {TopicsTable} from "./topics-table.component.tsx";
import {TopicDto} from "../../api/topics/dto/topics-response.dto.ts";
import {getTopics} from "../../api/topics/useGetTopics.ts";
import { api } from "../../core/api.ts";
import {Typography} from "@mui/material";
import {StudentDto} from "../../api/students/dto/students-response.dto.ts";

export const Topics = () => {
    const [selectedRows, setSelectedRows] = useState<TopicDto[]>([]);
    const [topics, setTopics] = useState<TopicDto[]>([])
    const {selectedStudentIds} = useStudents();
    const navigate = useNavigate();
    const { setTitles } = useTitles();

    const {selectedTopics, setSelectedTopics} = useSelectedTopics();

    useEffect(() => {
        getTopics().then((topics) => {
            setTopics(topics);
            setTitles(topics);
        });
    }, []);

    useEffect(() => {
        setSelectedTopics(selectedRows);
    }, [selectedRows, setSelectedTopics]);




    const sendToBackend = async (selectedRows: TopicDto[] | undefined, selectedStudentId: StudentDto[] | null) => {
        try {
            const response = await api.post('/tasks/unfinished', {
                topics: selectedRows,
                students: selectedStudentId,
            });

            const tasks = response.data;
            navigate('/tasks', { state: { tasks } });
        } catch (error) {
            console.error('Error sending data to backend:', error);
        }
    };

    const handleNextButtonClick = () => {
         sendToBackend(selectedRows, selectedStudentIds);
    };

    return (
        <>
            <Typography variant="h4" component="h4" sx={{mb: 2}} className="text-green-primary">
                Теми:
            </Typography>

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
