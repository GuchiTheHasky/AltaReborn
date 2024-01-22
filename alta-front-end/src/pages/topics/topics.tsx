import {Button} from "../../components/buttons/green-button.component.tsx";
import {DataGrid} from "@mui/x-data-grid";
import {columns, topicsArray} from "../../modules/topics/content/table-columns.content.ts";
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {useStudents} from "../../context/data-provider.context.tsx";
import {TopicsTable} from "./topics-table.component.tsx";

export const Topics = () => {
    const [selectedRows, setSelectedRows] = useState<number[]>([]);
    const {selectedStudentIds} = useStudents();
    const navigate = useNavigate();

    // const sendToBackend = async (selectedRows: number[] | undefined, selectedStudentId: string | null) => {
    //     const topicsIds = selectedRows?.map(topic => topic).join(',');
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

    const handleNextButtonClick = () => {
        console.log("Вибрані id тем:", selectedRows);
        console.log("Вибраний студент:", selectedStudentIds);
        // sendToBackend(selectedRows, selectedStudentIds);
    };

    return (
        <>
            <h2 className="text-green-primary">Теми:</h2>

            <div className="min-h-[500px]">
                {/*<DataGrid*/}
                {/*    rows={topicsArray}*/}
                {/*    columns={columns}*/}
                {/*    initialState={{*/}
                {/*        pagination: {*/}
                {/*            paginationModel: {page: 0, pageSize: 10},*/}
                {/*        },*/}
                {/*    }}*/}
                {/*    pageSizeOptions={[5, 10, 20, 50]}*/}
                {/*    checkboxSelection*/}
                {/*    onRowSelectionModelChange={(newSelection) => setSelectedRows(newSelection as number[])}*/}
                {/*/>*/}
                <TopicsTable setSelectedTopicIds={setSelectedRows} selectedTopicIds={selectedRows} topics={topicsArray}/>
            </div>

            <div className={'flex justify-around mt-4 gap-5'}>
                <Button className={'flex-1 '} color={"yellow"} label="НАЗАД" onClick={() => window.history.back()}/>

                <Button className={'flex-1 '} color={"green"} label="ДАЛІ" onClick={handleNextButtonClick}/>
            </div>
        </>
    );
};
