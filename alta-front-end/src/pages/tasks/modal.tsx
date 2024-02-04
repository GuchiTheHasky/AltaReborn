import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {FC, useState} from "react";
import {MenuItem, Select, TextField} from "@mui/material";
import {useTitles} from "../../context/data-provider.context.tsx";
import {TaskDto} from "../../api/tasks/dto/tasks-response.dto.ts";

const style = {
    position: 'absolute' as const,
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

interface BasicModalProps {
    isOpen: boolean;
    onClose: () => void;
    selectedTask: TaskDto | null;
}

const BasicModal: FC<BasicModalProps> = (props) => {
    const { isOpen, onClose, selectedTask } = props;

    const [level, setLevel] = useState("");
    const [title, setTitle] = useState("");
    const [answer, setAnswer] = useState("");

    const { titles } = useTitles();

    const handleSave = () => {
        console.log("level", level);
        console.log("title", title);
        console.log("answer", answer);
    }
    const handleOpen = () => onClose();
    const handleClose = () => onClose();

    return (
        <div>
            <Button onClick={handleOpen}>Open modal</Button>
            <Modal
                open={isOpen}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Редагувати завдання
                    </Typography>

                    <TextField
                        label="Рівень"
                        variant="outlined"
                        margin="normal"
                        fullWidth
                        value={level}
                        onChange={(e) => setLevel(e.target.value)}
                    />

                    <Select
                        label="Тема"
                        variant="outlined"
                        fullWidth
                        value={title}
                        onChange={(e) => setTitle(e.target.value as string)}
                    >
                        {titles.map((title) => (
                            <MenuItem value={title.title} key={title.title}>{title.title}</MenuItem>
                        ))}
                    </Select>

                    <TextField
                        label="Відповідь"
                        variant="outlined"
                        margin="normal"
                        fullWidth
                        value={answer}
                        onChange={(e) => setAnswer(e.target.value)}
                    />
                </Box>
            </Modal>
        </div>
    );
}

export default BasicModal;
