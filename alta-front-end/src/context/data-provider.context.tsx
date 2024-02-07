import {createContext, FC, PropsWithChildren, useContext, useState} from "react";
import {TopicDto} from "../api/topics/dto/topics-response.dto.ts";
import {StudentDto} from "../api/students/dto/students-response.dto.ts";

export const DataProviderContext = createContext<DataProviderContextType>({} as DataProviderContextType);

export interface DataProviderContextType {
    selectedStudentIds: StudentDto[];
    setSelectedStudentIds: (value: StudentDto[]) => void;
    titles: TopicDto[];
    setTitles: (value: TopicDto[]) => void;
    selectedTopics: TopicDto[];
    setSelectedTopics: (value: TopicDto[]) => void;
}

export const DataProviderContextProvider: FC<PropsWithChildren> = ({children}) => {
    const [selectedStudentIds, setSelectedStudentIds] = useState<StudentDto[]>([]);
    const [titles, setTitles] = useState<TopicDto[]>([]);
    const [selectedTopics, setSelectedTopics] = useState<TopicDto[]>([]);

    return <DataProviderContext.Provider value={{
        selectedStudentIds,
        setSelectedStudentIds,
        selectedTopics,
        setSelectedTopics,
        titles,
        setTitles
    }}>
        {children}
    </DataProviderContext.Provider>

}

export const useStudents = () => {
    const {selectedStudentIds, setSelectedStudentIds} = useContext(DataProviderContext);
    return {
        selectedStudentIds,
        setSelectedStudentIds
    }
}

export const useTitles = () => {
    const {titles, setTitles} = useContext(DataProviderContext);
    return {
        titles,
        setTitles
    }
}

export const useSelectedTopics = () => {
    const {selectedTopics, setSelectedTopics} = useContext(DataProviderContext);
    return {
        selectedTopics,
        setSelectedTopics
    }
}
