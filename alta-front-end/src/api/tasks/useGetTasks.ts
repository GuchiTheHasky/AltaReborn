import { api } from '../../core/api';
import { useQuery } from '@tanstack/react-query';


const getTasks = async () => {
    const { data } = await api.get('/tasks/all');

    return data;
};

export const useGetTasks = () => {
    const query = useQuery({
        queryKey: ['tasks'],
        queryFn: getTasks,
    });

    return query;
};