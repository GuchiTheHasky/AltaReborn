import {StudentDto} from "../../students/dto/students-response.dto.ts";

export interface TaskDto {
    id: number;
    imagePath: string;
    level: string;
    text: string;
    answer: string;
    title: string;
    status: string;
    topicId: number; // todo: delete this line ???
    isDeleted: boolean; // todo: delete this line ???
    students: StudentDto[]; // todo: delete this line ???
}