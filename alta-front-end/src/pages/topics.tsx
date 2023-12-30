import { GreenButton } from "../components/buttons/green-button.component.tsx";
import { TopicsTable } from "../modules/topics/topics-table.component.tsx";
import { useGetTopics } from "../api/topics/useGetTopics.ts";
import { Backdrop, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import {TopicResponse} from "../api/topics/dto/topics-response.dto.ts";
import {useState} from "react";
import {StudentResponse} from "../api/students/dto/students-response.dto.ts";

export const Topics = () => {
  const { data: topics, isLoading } = useGetTopics();
  const navigate = useNavigate();
  const [selectedTopic, setSelectedTopic] = useState<TopicResponse[]>();
// todo : StudentResponse | null | undefined
  const [selectedStudent, setSelectedStudent] = useState<StudentResponse>();

  const sendToBackend = async (topics: TopicResponse[] | undefined, student: StudentResponse | null) => {
    try {
      const response = await fetch('/api/v1/tasks/fetch', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ topics, student }),
      });

      if (response.ok) {
        console.log('Data sent successfully');
        // Додайте код для обробки успішної відправки, якщо потрібно
      } else {
        console.error('Failed to send data to the backend');
        // Додайте код для обробки помилки відправки, якщо потрібно
      }
    } catch (error) {
      console.error('Error sending data to the backend:', error);
      // Додайте код для обробки помилки відправки, якщо потрібно
    }
  };
  const handleButtonClick = () => {
    if (selectedTopic && selectedStudent) {
      // Відправляємо дані на бекенд
      sendToBackend(selectedTopic, selectedStudent);

      // Переходимо на іншу сторінку
      navigate('/tasks');
    } else {
      // Show alert message
      console.warn('Будь ласка, виберіть тему і студента перед переходом.');
    }
  };
  // const handleButtonClick = () => {
  //   if (selectedTopic) {
  //     // Navigate to another page on GreenButton click
  //     navigate('/tasks');
  //   } else {
  //       // Show alert message
  //       console.warn('Будь ласка, виберіть тему перед переходом.');
  //   }
  //   // Navigate to another page on GreenButton click
  // };

  return (
    <div>
      {isLoading && (
        <Backdrop open={isLoading}>
          <CircularProgress color="inherit" />
        </Backdrop>
      )}
      <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
        <h2 className="text-green-primary w-[700px]">Теми:</h2>
        <TopicsTable topics={topics} onSelectTopic={setSelectedTopic} onSelectStudent={setSelectedStudent} />
        <div className="w-[300px]">
          <GreenButton label="ДАЛІ" onClick={handleButtonClick} />
        </div>
      </div>
    </div>
  );
};

