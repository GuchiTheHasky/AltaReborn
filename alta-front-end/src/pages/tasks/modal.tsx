import Modal from '@mui/material/Modal';
import {FC} from "react";

interface EditTaskModalProps {
    taskId: number;
    onClose: () => void;
}

export const EditTaskModal: FC<EditTaskModalProps> = ({taskId, onClose}) => {

    return (
        <Modal open={true} onClose={onClose}>

            Edit form here
        </Modal>
    );
};