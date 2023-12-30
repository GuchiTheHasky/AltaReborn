import { DataGrid, GridRowId } from '@mui/x-data-grid';
import { columns } from './content/table-columns.content.tsx';
import { useState, FC } from 'react';
import { useAppContext } from "../../api/context/appContext.tsx";
import { StudentResponse } from "../../api/students/dto/students-response.dto.ts";

interface StudentsTableProps {
    onSelectStudent: (selectedStudent: StudentResponse) => void;
}

export const StudentsTable: FC<StudentsTableProps> = ({ onSelectStudent }) => {
    const { students } = useAppContext();
    const [selectionModel, setSelectionModel] = useState<GridRowId[]>([]);

    const handleRowSelection = (selectedRows: GridRowId[]) => {
        setSelectionModel(selectedRows);

        if (selectedRows.length > 0) {
            const selectedStudent = students[selectedRows[0] as number];

            if (selectedStudent) {
                onSelectStudent(selectedStudent);
            } else {
                console.error('Selected student is undefined or null');
            }
        }
    };

    return (
        <div className="h-[500px] w-[700px]">
            <DataGrid
                rows={Object.values(students)}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: { page: 0, pageSize: 10 },
                    },
                }}
                sx={{
                    '& .MuiDataGrid-columnHeaderCheckbox .MuiDataGrid-columnHeaderTitleContainer': {
                        display: 'none',
                    },
                }}
                pageSizeOptions={[5, 10]}
                rowSelectionModel={selectionModel}
                onRowSelectionModelChange={handleRowSelection}
            />
        </div>
    );
};

