import { GreenButton } from "../components/buttons/green-button.component.tsx";
import { TopicsTable } from "../modules/topics/topics-table.component.tsx";
import { useGetTopics } from "../api/topics/useGetTopics.ts";
import { Backdrop, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import {TopicResponse} from "../api/topics/dto/topics-response.dto.ts";
import {useState} from "react";
import {StudentResponse} from "../api/students/dto/students-response.dto.ts";
import {api} from "../core/api.ts";
import {useAppContext} from "../api/context/appContext.tsx";

export const Topics = () => {
  const { data: topics, isLoading } = useGetTopics();
  const navigate = useNavigate();
  const [selectedTopic, setSelectedTopic] = useState<TopicResponse[]>();
  const { selectedStudent } = useAppContext();


  const sendToBackend = async (topics: TopicResponse[] | undefined, student: StudentResponse | null) => {
      const topicsIds = topics?.map(topic => topic.id).join(',');
      const response = await api.get('/tasks/fetch', {
          params: {
              topics: topicsIds,
              student: student?.id,
          },
          headers: {
              'Content-Type': 'application/json',
          },
      });
      const tasks = response.data;
      navigate('/tasks', { state: { tasks } });
      console.log('response tasks: ', tasks);
  };

  const handleButtonClick = () => {
      try {
          sendToBackend(selectedTopic, selectedStudent);
      }
        catch (e) {
            console.log('Error: ', e);
        }
  };

  return (
    <div>
      {isLoading && (
        <Backdrop open={isLoading}>
          <CircularProgress color="inherit" />
        </Backdrop>
      )}
      <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
        <h2 className="text-green-primary w-[700px]">Теми:</h2>
        <TopicsTable topics={topics} onSelectTopic={setSelectedTopic} />
        <div className="w-[300px]">
          <GreenButton label="ДАЛІ" onClick={handleButtonClick} />
        </div>
      </div>
    </div>
  );
};

