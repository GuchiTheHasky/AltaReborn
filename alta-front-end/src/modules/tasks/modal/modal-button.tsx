import Button from '@mui/material/Button';
import {useState} from "react";
import {FormControl, InputLabel, MenuItem, Modal, Select, TextField} from "@mui/material";
import {topicsArray} from "../../topics/content/table-columns.content.ts";
import {api} from "../../../core/api.ts";
import {useNavigate} from "react-router-dom";

const ModalButton = ({ currentTask }) => {
    const [open, setOpen] = useState<boolean>(false);
    const [selectedTopic, setSelectedTopic] = useState<string>('');
    const [level, setLevel] = useState<string>('');
    const [answer, setAnswer] = useState<string>('');
    const navigate = useNavigate();

    const handleOpenModal = () => {
        setOpen(true);
        setSelectedTopic(currentTask.id);
        setLevel(currentTask.level);
        setAnswer(currentTask.answer);
    };

    const handleCloseModal = () => {
        setOpen(false);
    };

    const handleSaveChanges = async () => {
        try {
            const taskData = {
                id: currentTask.id,
                title: selectedTopic,
                level: level,
                answer: answer,
            };

            const response = await api.put(`/tasks/${currentTask.id}`, taskData);
            const tasks = response.data;
            navigate('/tasks', {state: {tasks}});
            console.log('Task saved successfully:', response.data);
        } catch (error) {
            console.error('Error saving task:', error);
        }

        // Закриття модального вікна
        handleCloseModal();
    };

    return (
        <>
            <div className="p-10 flex justify-center w-full">
                <Button color='primary' onClick={handleOpenModal}>Edit</Button>

                <Modal open={open} onClose={handleCloseModal}>
                    <div className='absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 h-96 w-80 shadow-xl p-4 border-2 border-black bg-white'>
                        <FormControl className="flex gap-3" fullWidth>
                            <InputLabel id="select-label">Select Option</InputLabel>
                            <Select
                                id="select"
                                value={selectedTopic}
                                label="Оберіть тему"
                                onChange={(e) => setSelectedTopic(e.target.value as string)}
                            >
                                {topicsArray.map((topic) => (
                                    <MenuItem key={topic.id} value={topic.id}>
                                        {topic.title}
                                    </MenuItem>
                                ))}
                            </Select>

                            <TextField
                                label="Рівень"
                                value={level}
                                onChange={event => setLevel(event.target.value)}
                                fullWidth
                                className="mb-2"
                            />

                            <TextField
                                label="Відповідь"
                                value={answer}
                                onChange={event => setAnswer(event.target.value)}
                                fullWidth
                                className="mb-2"
                            />

                            <Button variant="contained" color="primary" onClick={handleSaveChanges}>
                                Save
                            </Button>
                        </FormControl>
                    </div>
                </Modal>
            </div>
        </>
    );
};

export default ModalButton;
