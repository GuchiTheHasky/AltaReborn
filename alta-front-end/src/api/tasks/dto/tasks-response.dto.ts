interface TaskDto {
    id: number;
    imagePath: string;
    level: string;
    text: string;
    answer: string;
    title: string;
    topicId: number;
    isDeleted: boolean;
}

export interface TasksResponse {
    unfinishedTasksForAllStudentsSelected: TaskDto[];
    tasksCompletedByAtLeastOneStudent: TaskDto[];
}