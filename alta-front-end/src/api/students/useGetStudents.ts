import { api } from '../../core/api';

export const getStudents = async () => {
    const { data } = await api.get('/students');
    return data;
};
