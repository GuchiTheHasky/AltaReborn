import React, {createContext, useContext, useState, ReactNode, useEffect} from 'react';
import {api} from "../../core/api.ts";

const AppContext = createContext<ContextType | undefined>(undefined);

interface Student {
    id: number;
    firstName: string;
    lastName: string;
    grade: string;
    comment: string;
}
interface Topic {
    id: number;
    title: string;
}

interface ContextType {
    selectedStudent: Student | null;
    setSelectedStudent: React.Dispatch<React.SetStateAction<Student | null>>;
    students: { [id: number]: Student }; // Тип об'єкту для students
    setStudents: React.Dispatch<React.SetStateAction<{ [id: number]: Student }>>;
    topics: Topic[];
    setTopics: React.Dispatch<React.SetStateAction<Topic[]>>;
}

export const AppProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [students, setStudents] = useState<{ [id: number]: Student }>({});

    const [topics, setTopics] = useState<Topic[]>([]);

    const [selectedStudent, setSelectedStudent] = useState<Student | null>(null);

    const contextValue: ContextType = {
        selectedStudent,
        setSelectedStudent: (student) => {
            console.log('Setting selected student:', student);
            setSelectedStudent(student);
        },
        students,
        setStudents,
        topics,
        setTopics,
    };

    useEffect(() => {
        const fetchStudents = async () => {
            try {
                const { data } = await api.get('/students');
                const studentsMap: { [id: number]: Student } = {};
                data.forEach((student: Student) => {
                    studentsMap[student.id] = student;
                });
                setStudents(studentsMap);
            } catch (error) {
                console.error('Error fetching students:', error);
            }
        };

        fetchStudents();
    }, []);

    return (
        <AppContext.Provider value={contextValue}>
            {children}
        </AppContext.Provider>
    );
};

export const useAppContext = (): ContextType => {
    const context = useContext(AppContext);
    if (!context) {
        throw new Error('useAppContext має бути використаний в середовищі AppProvider');
    }
    return context;
};
