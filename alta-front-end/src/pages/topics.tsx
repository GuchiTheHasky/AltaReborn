import {GreenButton} from "../components/buttons/green-button.component.tsx";
import {YellowButton} from "../components/buttons/yellow-button.component.tsx";
import {DataGrid} from "@mui/x-data-grid";
import {columns, topicsArray} from "../modules/topics/content/table-columns.content.ts";
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {api} from "../core/api.ts";

export const Topics = () => {
    const [selectedRows, setSelectedRows] = useState<number[]>([]);
    const selectedStudentId = localStorage.getItem("studentId");
    const navigate = useNavigate();

    const sendToBackend = async (selectedRows: number[] | undefined, selectedStudentId: string | null) => {
    const topicsIds = selectedRows?.map(topic => topic).join(',');
    const response = await api.get('/tasks/test', {
        params: {
            topics: topicsIds,
            student: selectedStudentId,
        },
        headers: {
            'Content-Type': 'application/json',
        },
    });
    const tasks = response.data;
    navigate('/tasks', {state: {tasks}});
    console.log('response tasks: ', tasks);
};

    const handleNextButtonClick = () => {
        console.log("Вибрані id тем:", selectedRows);
        console.log("Вибраний студент:", selectedStudentId);
        sendToBackend(selectedRows, selectedStudentId);
    };

    return (
        <div>
            <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
                <h2 className="text-green-primary w-[700px]">Теми:</h2>

                <div className="h-[500px] w-[700px]">
                    <DataGrid
                        rows={topicsArray}
                        columns={columns}
                        initialState={{
                            pagination: {
                                paginationModel: {page: 0, pageSize: 10},
                            },
                        }}
                        pageSizeOptions={[5, 10, 20, 50]}
                        checkboxSelection
                        onRowSelectionModelChange={(newSelection) => setSelectedRows(newSelection as number[])}
                    />
                </div>

                <div className="flex space-x-4">
                    <div className="w-[300px]">
                        <YellowButton label="НАЗАД" onClick={() => window.history.back()}/>
                    </div>

                    <div className="w-[300px]">
                        <GreenButton label="ДАЛІ" onClick={handleNextButtonClick}/>
                    </div>
                </div>

            </div>
        </div>
    );
};












// const storedStudentId = localStorage.getItem("studentId");
//
// const handleButtonClick = () => {
//
// };

//const navigate = useNavigate();

// const sendToBackend = async (topics: TopicResponse[] | undefined, selectedStudentId: number | null) => {
//     const topicsIds = topics?.map(topic => topic.title).join(',');
//     const response = await api.get('/tasks/test', {
//         params: {
//             topics: topicsIds,
//             student: selectedStudentId,
//         },
//         headers: {
//             'Content-Type': 'application/json',
//         },
//     });
//     const tasks = response.data;
//     navigate('/tasks', {state: {tasks}});
//     console.log('response tasks: ', tasks);
// };

// useEffect(() => {
//     const storedStudentId = localStorage.getItem("studentId");
//     console.log("Отриманий студент:", storedStudentId);
// });


//
//
// import {GreenButton} from "../components/buttons/green-button.component.tsx";
// import {TopicsTable} from "../modules/topics/topics-table.component.tsx";
// import {useGetTopics} from "../api/topics/useGetTopics.ts";
// import {Backdrop, CircularProgress} from '@mui/material';
// import {useNavigate} from 'react-router-dom';
// import {TopicResponse} from "../api/topics/dto/topics-response.dto.ts";
// import {useState} from "react";
// import {StudentResponse} from "../api/students/dto/students-response.dto.ts";
// import {api} from "../core/api.ts";
// import {useAppContext} from "../api/context/appContext.tsx";
// import {YellowButton} from "../components/buttons/yellow-button.component.tsx";
//
// export const Topics = () => {
//     const {data: topics, isLoading} = useGetTopics();
//     const navigate = useNavigate();
//     const [selectedTopic, setSelectedTopic] = useState<TopicResponse[]>();
//     const {selectedStudent} = useAppContext();
//
//     const backToStudents = () => {
//         window.history.back();
//     };
//
//     const sendToBackend = async (topics: TopicResponse[] | undefined, student: StudentResponse | null) => {
//         const topicsIds = topics?.map(topic => topic.id).join(',');
//         const response = await api.get('/tasks/unfinished', {
//             params: {
//                 topics: topicsIds,
//                 student: student?.id,
//             },
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//         });
//         const tasks = response.data;
//         navigate('/tasks', {state: {tasks}});
//         console.log('response tasks: ', tasks);
//     };
//
//     const handleButtonClick = () => {
//         try {
//             sendToBackend(selectedTopic, selectedStudent);
//         } catch (e) {
//             console.log('Error: ', e);
//         }
//     };
//
//     return (
//         <div>
//             {isLoading && (
//                 <Backdrop open={isLoading}>
//                     <CircularProgress color="inherit"/>
//                 </Backdrop>
//             )}
//             <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
//                 <h2 className="text-green-primary w-[700px]">Теми:</h2>
//                 <TopicsTable topics={topics} onSelectTopic={setSelectedTopic}/>
//
//                 <div className="flex space-x-4">
//                     <div className="w-[300px]">
//                         <YellowButton label="НАЗАД" onClick={backToStudents}/>
//                     </div>
//                     <div className="w-[300px]">
//                         <GreenButton label="ДАЛІ" onClick={handleButtonClick}/>
//                     </div>
//                 </div>
//
//             </div>
//         </div>
//     );
// };

