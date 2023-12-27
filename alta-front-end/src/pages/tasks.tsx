import { TasksTable } from '../modules/tasks/tasks-table.component'
import { useGetTasks } from '../api/tasks/useGetTasks';
import { Backdrop, CircularProgress } from '@mui/material';


export const Tasks = () => {
    const { data: tasks, isLoading } = useGetTasks();

    return (
        <div>
            {isLoading && (
                <Backdrop open={isLoading}>
                    <CircularProgress color="inherit" />
                </Backdrop>
            )}
            <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
                <h2 className="text-green-primary w-[700px]">Завдання:</h2>
                <TasksTable tasks={tasks} />
            </div>
        </div>
    );
};