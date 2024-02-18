import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { FC, useEffect, useState } from "react";
import { FormControl, InputLabel, MenuItem, Select, TextField } from "@mui/material";
import { useTitles } from "../../context/data-provider.context.tsx";
import { TaskDto } from "../../api/tasks/dto/tasks-response.dto.ts";
import {Button} from '../../components/buttons/green-button.component.tsx';
import {api} from "../../core/api.ts";


const style = {
    position: 'absolute',
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
    reloadData: () => void;
}

const BasicModal: FC<BasicModalProps> = ({ isOpen, onClose, selectedTask , reloadData}) => {
    const [level, setLevel] = useState("");
    const [title, setTitle] = useState("");
    const [answer, setAnswer] = useState("");

    const { titles } = useTitles();

    useEffect(() => {
        if (selectedTask) {
            setLevel(selectedTask.level || "");
            setTitle(selectedTask.title || "");
            setAnswer(selectedTask.answer || "");
        }
    }, [selectedTask]);

    const handleSave = async () => {
        const id : number | undefined = selectedTask?.id;
        const updatedTask = {
            level,
            title,
            answer,
        };
        try {
            await api.put(`/tasks/${id}`, updatedTask);
            onClose();
            reloadData();
        } catch (error) {
            console.error('Error updating task:', error);
        }
    };

    return (
        <div>
            <Modal
                open={isOpen}
                onClose={onClose}
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

                    <FormControl fullWidth margin="normal">
                        <InputLabel id="task-title-label">Тема</InputLabel>
                        <Select
                            labelId="task-title-label"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            label="Тема"
                        >
                            {titles.map((item) => (
                                <MenuItem value={item.title} key={item.title}>{item.title}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <TextField
                        label="Відповідь"
                        variant="outlined"
                        margin="normal"
                        fullWidth
                        value={answer}
                        onChange={(e) => setAnswer(e.target.value)}
                    />

                    <Button
                        onClick={handleSave}
                        color={"green"}
                        label="Зберегти"
                    />
                </Box>
            </Modal>
        </div>
    );
}

export default BasicModal;
