import React, {createContext, useContext, useState, ReactNode, useEffect} from 'react';
import {api} from "../../core/api.ts";

// Створюємо новий контекст
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

// Компонент-постачальник для надання стану контексту
export const AppProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    // Стан для студентів
    const [students, setStudents] = useState<{ [id: number]: Student }>({});

    // Стан для тем
    const [topics, setTopics] = useState<Topic[]>([]);

    // Стан для вибраного студента
    const [selectedStudent, setSelectedStudent] = useState<Student | null>(null);

    // Задаємо значення для дочірніх компонентів
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
    }, []); // Fetch students on component mount

    return (
        <AppContext.Provider value={contextValue}>
            {children}
        </AppContext.Provider>
    );
};

// Хук для використання значень з контексту
export const useAppContext = (): ContextType => {
    const context = useContext(AppContext);
    if (!context) {
        throw new Error('useAppContext має бути використаний в середовищі AppProvider');
    }
    return context;
};



// import React, { createContext, useContext, useState, ReactNode } from 'react';
//
// // Створюємо новий контекст
// const AppContext = createContext<ContextType | undefined>(undefined);
//
// interface Student {
//     id: number;
//     firstName: string;
//     lastName: string;
//     grade: string,
//     comment: string
// }
// interface Topic {
//     id: number;
//     title: string;
// }
//
// interface ContextType {
//     selectedStudent: Student | null;
//     setSelectedStudent: React.Dispatch<React.SetStateAction<Student | null>>;
//     topics: Topic[]; // Замініть "any[]" на тип тем у вашому випадку
//     setTopics: React.Dispatch<React.SetStateAction<Topic[]>>; // Замініть "any[]" на тип тем у вашому випадку
// }
//
// // Компонент-постачальник для надання стану контексту
// export const AppProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
//     // Стан для студентів
//     const [students, setStudents] = useState<Student[]>([]); // Замініть "any[]" на тип студентів у вашому випадку
//
//     // Стан для тем
//     const [topics, setTopics] = useState<Topic[]>([]); // Замініть "any[]" на тип тем у вашому випадку
//
//     // Задаємо значення для дочірніх компонентів
//     const contextValue: ContextType = {
//         students,
//         setStudents,
//         topics,
//         setTopics,
//     };
//
//     return (
//         <AppContext.Provider value={contextValue}>
//             {children}
//         </AppContext.Provider>
//     );
// };
//
// // Хук для використання значень з контексту
// export const useAppContext = (): ContextType => {
//     const context = useContext(AppContext);
//     if (!context) {
//         throw new Error('useAppContext має бути використаний в середовищі AppProvider');
//     }
//     return context;
// };
