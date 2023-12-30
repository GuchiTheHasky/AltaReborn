import {DataGrid, GridRowId} from "@mui/x-data-grid";
import { columns } from "./content/table-columns.content";
import React, { useEffect, useState, FC } from "react";
import { TopicResponse } from "../../api/topics/dto/topics-response.dto";

interface TopicsTableProps {
    topics: TopicResponse[] | undefined;
    //onSelectTopic: (selectedTopic: TopicResponse | TopicResponse[]) => void;
    onSelectTopic: React.Dispatch<React.SetStateAction<TopicResponse | TopicResponse[] | null>>;

}

export const TopicsTable: FC<TopicsTableProps> = ({ topics, onSelectTopic }) => {
    const [loadedTopics, setLoadedTopics] = useState<TopicResponse[]>([
        {
            id: 1,
            title: "",
        },
    ]);
    const [selectionModel, setSelectionModel] = useState<GridRowId[]>([]);

    useEffect(() => {
        if (topics) {
            setLoadedTopics(topics);
        }
    }, [topics]);

    const handleRowSelection = (selectedRows: GridRowId[]) => {
        setSelectionModel(selectedRows);

        const selectedTopics = loadedTopics.filter(topic => selectedRows.includes(topic.id));
        onSelectTopic(selectedTopics);
    };


    return (
        <>
        <div className="h-[500px] w-[700px]">
            <DataGrid
                rows={loadedTopics}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {page: 0, pageSize: 10},
                    },
                }}
                pageSizeOptions={[5, 10, 20, 50]}
                checkboxSelection
                rowSelectionModel={selectionModel}
                onRowSelectionModelChange={handleRowSelection}
            />
        </div>
        </>
    );
};