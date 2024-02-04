import {StudentDto} from "../../students/dto/students-response.dto.ts";

export interface TaskDto {
    id: number;
    imagePath: string;
    level: string;
    text: string;
    answer: string;
    title: string;
    topicId: number;
    isDeleted: boolean;
    students: StudentDto[]; // todo: delete this line ???
}

export interface TasksResponse {
    unfinishedTasksForAllStudentsSelected: TaskDto[];
    tasksCompletedByAtLeastOneStudent: TaskDto[];
}