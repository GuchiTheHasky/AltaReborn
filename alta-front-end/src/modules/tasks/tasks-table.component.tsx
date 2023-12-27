import { DataGrid } from "@mui/x-data-grid";
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
      text: "",
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
    <div className="h-[500px] w-[700px]">
      <DataGrid
        rows={loadedTasks}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 10 },
          },
        }}
        pageSizeOptions={[5, 10]}
        checkboxSelection
      />
    </div>
  );
}