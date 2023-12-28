import {api} from '../../core/api';
import {useQuery} from '@tanstack/react-query';

const getStudents = async () => {
    const {data} = await api.get('/students');

    return data;
};

export const useGetStudents = () => {
    return useQuery({
        queryKey: ['students'],
        queryFn: getStudents,
    });
};
