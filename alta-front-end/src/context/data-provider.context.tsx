import {createContext, FC, PropsWithChildren, useContext, useState} from "react";

export const DataProviderContext = createContext<DataProviderContextType>({} as DataProviderContextType);

export interface DataProviderContextType {
    selectedStudentIds: number[];
    setSelectedStudentIds: (value: number[]) => void;
}

export const DataProviderContextProvider: FC<PropsWithChildren> = ({children}) => {
    const [selectedStudentIds, setSelectedStudentIds] = useState<number[]>([]);

    return <DataProviderContext.Provider value={{
        selectedStudentIds, setSelectedStudentIds
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