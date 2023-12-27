import { DataGrid } from "@mui/x-data-grid";
import { columns } from "./content/table-columns.content";
import { useEffect, useState, FC } from "react";
import { TopicResponse } from "../../api/topics/dto/topics-response.dto";

interface TopicsTableProps {
    topics: TopicResponse[] | undefined;
}

export const TopicsTable: FC<TopicsTableProps> = ({ topics }) => {
    const [loadedTopics, setLoadedTopics] = useState<TopicResponse[]>([
        {
            id: 1,
            title: "",
        },
    ]);

    useEffect(() => {
        if (topics) {
            setLoadedTopics(topics);
        }
    }, [topics]);

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
            />
        </div>
        </>
    );
};