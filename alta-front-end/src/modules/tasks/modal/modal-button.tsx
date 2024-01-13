import {Button as MaterialButton} from '@mui/material';
import React from 'react';
import {FC, useState} from 'react';
import EditModal from "./modal-edit-button.tsx";
import CreateIcon from '@mui/icons-material/Create';

interface Entity {
    id: number;
    title: string;
    level: number;
    answer: string;
}

interface ModalButtonProps {
    entity: Entity;
    id: number;
    onClick?: (id: number) => void;
}

export const ModalButton: FC<ModalButtonProps> = ({entity, onClick}: ModalButtonProps) => {
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);

    const handleEditClick = () => {
        if (onClick) {
            onClick(entity.id);
            console.log('handleEditClick/ModalButton.tsx, id: ', entity.id);
        }
        setIsEditModalOpen(true);
    };

    const handleEditModalClose = () => {
        setIsEditModalOpen(false);
    };

    return (
        <React.Fragment>

            <MaterialButton
                variant="contained"
                className="w-full"
                onClick={handleEditClick}
                startIcon={<CreateIcon/>}
                style={{
                    backgroundColor: '#fcc44d',
                    display: 'flex',
                    alignContent: 'center',
                }}
            />
            <EditModal open={isEditModalOpen} onClose={handleEditModalClose} entity={entity}/>

        </React.Fragment>

    );
};
