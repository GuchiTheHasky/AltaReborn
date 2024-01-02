import { api } from '../../core/api';
import { useQuery } from '@tanstack/react-query';
import { useAppContext } from '../context/appContext';
import {StudentResponse} from "./dto/students-response.dto.ts";

const getStudents = async () => {
    const { data } = await api.get('/students');
    return data;
};

export const useGetStudents = () => {
    const { setStudents } = useAppContext();

    const { data, error, isLoading } = useQuery({
        queryKey: ['students'],
        queryFn: getStudents,
    });


    if (data) {
        setStudents((prevStudents) => {
            const updatedStudents = { ...prevStudents };
            data.forEach((student: StudentResponse) => {
                updatedStudents[student.id] = student;
            });
            return updatedStudents;
        });
    }

    return { data, error, isLoading };
};

