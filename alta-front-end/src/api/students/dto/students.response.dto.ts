

export interface StudentResponse {
  id: number;
  firstName: string;
  lastName: string;
  class: string;
  status: StudentStatus;
}

export enum StudentStatus {
  REMOTE = 'REMOTE',
  SCHOOL = 'SCHOOL',
}