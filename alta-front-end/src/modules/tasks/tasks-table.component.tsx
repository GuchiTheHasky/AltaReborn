import {DataGrid} from "@mui/x-data-grid";
import { useEffect, useState, FC } from "react";
import { TasksResponse } from "../../api/tasks/dto/tasks-response.dto";
import { columns } from "./content/table-columns.content";

interface TasksTableProps {
  tasks: TasksResponse[] | undefined;
}

export const TasksTable: FC<TasksTableProps> = ({ tasks }) => {
  const [loadedTasks, setLoadedTasks] = useState<TasksResponse[]>([
    {
      id: 1,
      title: "",
      imagePath: "",
      level: "",
      textHtml: "",
      answer: "",
    },
  ]);



  useEffect(() => {
    if (tasks) {
      setLoadedTasks(tasks);
    }
  }, [tasks]);

    return (
      <div style={{height: '500px', width: '1400px'}}>
        <DataGrid
            rows={loadedTasks}
            columns={columns}
            initialState={{
                pagination: {
                    paginationModel: { page: 0, pageSize: 10 },
                },
            }}
            pageSizeOptions={[5, 10, 20, 50, 100]}
            checkboxSelection
            getRowHeight={() => 'auto'} getEstimatedRowHeight={() => 200}
        />
      </div>
  );
}