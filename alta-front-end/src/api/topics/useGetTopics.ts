import {api} from "../../core/api.ts";

export const getTopics = async () => {
    const { data } = await api.get('/topics');
    return data;
}