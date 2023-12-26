import { api } from '../../core/api';
import { useQuery } from '@tanstack/react-query';

const getTopics = async () => {
    const { data } = await api.get('/topics');

    return data;
};

export const useGetTopics = () => {
    const query = useQuery({
        queryKey: ['topics'],
        queryFn: getTopics,
    });

    return query;
};