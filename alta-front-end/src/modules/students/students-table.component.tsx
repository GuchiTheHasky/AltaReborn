import {DataGrid, GridRowId} from '@mui/x-data-grid';
import {columns} from './content/table-columns.content.tsx';
import {useEffect, useState, FC} from 'react';
import {StudentResponse} from '../../api/students/dto/students-response.dto';

interface StudentsTableProps {
    students: StudentResponse[] | undefined;
}

export const StudentsTable: FC<StudentsTableProps> = ({students}) => {
    const [loadedStudents, setLoadedStudents] = useState<StudentResponse[]>([
        {
            id: 1,
            firstName: '',
            lastName: '',
            grade: '',
            comment: '',
        },
    ]);
    const [selectionModel, setSelectionModel] = useState<GridRowId[]>([]);


    useEffect(() => {
        if (students) {
            setLoadedStudents(students);
        }
    }, [students]);

    return (
        <div className="h-[500px] w-[700px]">
            <DataGrid
                rows={loadedStudents}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {page: 0, pageSize: 10},
                    },
                }}
                sx={{
                    '& .MuiDataGrid-columnHeaderCheckbox .MuiDataGrid-columnHeaderTitleContainer': {
                        display: 'none',
                    },
                }}
                pageSizeOptions={[5, 10]}
                onRowSelectionModelChange={(selection) => {
                    if (selection.length > 1) {
                        const selectionSet = new Set(selectionModel);
                        const result = selection.filter((s) => !selectionSet.has(s));

                        setSelectionModel(result);
                    } else {
                        setSelectionModel(selection);
                    }
                }}
            />
        </div>
    );
};
