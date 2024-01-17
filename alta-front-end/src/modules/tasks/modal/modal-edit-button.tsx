import React, {useEffect, useState} from 'react';
import Modal from '@mui/joy/Modal';
import Sheet from '@mui/joy/Sheet';
import TextField from '@mui/material/TextField';
import {Button} from "@mui/joy";
import {api} from "../../../core/api.ts";
import {MenuItem, Select} from "@mui/material";
import {useGetTopics} from "../../../api/topics/useGetTopics.ts";

interface Entity {
    id: number;
    title: string;
    level: number;
    answer: string;
    topicId: number;
}

interface EditModalProps {
    open: boolean;
    onClose: () => void;
    entity: Entity;
}

const EditModal: React.FC<EditModalProps> = ({open, onClose, entity}) => {
    const [editedEntity, setEditedEntity] = useState<Entity>({...entity});
    const [selectedTheme, setSelectedTheme] = useState<string>('');
    const {data: topics} = useGetTopics();

    useEffect(() => {
        setEditedEntity({...entity});
        setSelectedTheme(entity.title);
    }, [entity]);

    const handleSave = async () => {
        try {
            console.log(' 2 handleSave, entity:', entity);
            console.log(' 2 handleSave, editedEntity:', editedEntity);
            console.log(' 2 handleSave, selectedTheme:', selectedTheme);
            console.log(' 2 handleSave, entity.title', entity.title);
            console.log(' 2 handleSave, entity.Topic.id', entity.topicId);
            console.log('topics:', topics);
            //console.log(' 2 handleSave, selectedTopic', topics.find((topic) => topic.title == selectedTheme));

            await api.post('/tasks/edit', editedEntity, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            onClose();
        } catch (error) {
            // Обробка помилок при відправленні запиту
            console.error('Error during save:', error);
        }
    };

    const handleChange = (field: keyof Entity, value: string | number) => {
        setEditedEntity((prevEntity) => ({
            ...prevEntity,
            [field]: value,
        }));

        // Оновлення вибраної теми
        if (field === 'title') {
            setSelectedTheme(value as string);
            entity.topicId = topics.find((topic) => topic.title === value)?.id || 0;
            setEditedEntity((prevEntity) => ({
                ...prevEntity,
                topicId: entity.topicId, // Оновити editedEntity.topicId
            }));
        }
    };


    if (!topics) return <div>Loading...</div>;


    return (
        <Modal
            aria-labelledby="modal-title"
            aria-describedby="modal-desc"
            open={open}
            onClose={onClose}
            sx={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}
        >
            <Sheet
                variant="outlined"
                sx={{
                    maxWidth: 500,
                    maxHeight: 600,
                    borderRadius: 'md',
                    p: 3,
                    boxShadow: 'lg',
                    '& > *': {
                        marginBottom: 2, // todo: find something better
                    },
                }}
            >

                <Select
                    label="Тема"
                    fullWidth
                    value={editedEntity.title}
                    onChange={(e) => handleChange('title', e.target.value)}
                >

                    {topics.map((topic) => (
                        <MenuItem key={topic.title} value={topic.title}>
                            {topic.title}
                        </MenuItem>
                    ))}
                </Select>
                <TextField label="Рівень"
                           fullWidth
                           value={editedEntity.level || '0'}
                           onChange={(e) => handleChange('level', parseInt(e.target.value, 10))}
                />
                <TextField label="Відповідь"
                           fullWidth
                           value={editedEntity.answer}
                           onChange={(e) => handleChange('answer', e.target.value)}
                />

                <Button onClick={handleSave} color="primary">
                    Save
                </Button>
            </Sheet>
        </Modal>
    );
};

export default EditModal;



// import React, {useEffect, useState} from 'react';
// import Modal from '@mui/joy/Modal';
// import Sheet from '@mui/joy/Sheet';
// import TextField from '@mui/material/TextField';
// import {Button} from "@mui/joy";
// import {api} from "../../../core/api.ts";
// import {MenuItem, Select} from "@mui/material";
// import {useGetTopics} from "../../../api/topics/useGetTopics.ts";
//
// interface Entity {
//     id: number;
//     title: string;
//     level: number;
//     answer: string;
//     topicId: number;
// }
//
// interface EditModalProps {
//     open: boolean;
//     onClose: () => void;
//     entity: Entity;
// }
//
// const EditModal: React.FC<EditModalProps> = ({open, onClose, entity}) => {
//     const [editedEntity, setEditedEntity] = useState<Entity>({...entity});
//     const [selectedTheme, setSelectedTheme] = useState<string>('');
//     const {data: topics} = useGetTopics();
//
//     useEffect(() => {
//         setEditedEntity({...entity});
//         setSelectedTheme(entity.title);
//     }, [entity]);
//
//     const handleSave = async () => {
//         try {
//             console.log(' 2 handleSave, entity:', entity);
//             console.log(' 2 handleSave, editedEntity:', editedEntity);
//             console.log(' 2 handleSave, selectedTheme:', selectedTheme);
//             console.log(' 2 handleSave, entity.title', entity.title);
//             console.log(' 2 handleSave, entity.Topic.id', entity.topicId);
//             console.log('topics:', topics);
//             //console.log(' 2 handleSave, selectedTopic', topics.find((topic) => topic.title == selectedTheme));
//
//             await api.post('/tasks/edit', editedEntity, {
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//             });
//
//             onClose();
//         } catch (error) {
//             // Обробка помилок при відправленні запиту
//             console.error('Error during save:', error);
//         }
//     };
//
//     const handleChange = (field: keyof Entity, value: string | number) => {
//         setEditedEntity((prevEntity) => ({
//             ...prevEntity,
//             [field]: value,
//         }));
//
//         // Оновлення вибраної теми
//         if (field === 'title') {
//             setSelectedTheme(value as string);
//             entity.topicId = topics.find((topic) => topic.title == value)?.id || 0;
//         }
//         console.log('editedEntity after update:', editedEntity);
//         console.log('selectedTheme after update:', selectedTheme);
//         console.log('entity.title after update:', entity.title);
//     };
//
//     if (!topics) return <div>Loading...</div>;
//
//
//     return (
//         <Modal
//             aria-labelledby="modal-title"
//             aria-describedby="modal-desc"
//             open={open}
//             onClose={onClose}
//             sx={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}
//         >
//             <Sheet
//                 variant="outlined"
//                 sx={{
//                     maxWidth: 500,
//                     maxHeight: 600,
//                     borderRadius: 'md',
//                     p: 3,
//                     boxShadow: 'lg',
//                     '& > *': {
//                         marginBottom: 2, // todo: find something better
//                     },
//                 }}
//             >
//
//                 <Select
//                     label="Тема"
//                     fullWidth
//                     value={editedEntity.title}
//                     onChange={(e) => handleChange('title', e.target.value)}
//                 >
//
//                     {topics.map((topic) => (
//                         <MenuItem key={topic.title} value={topic.title}>
//                             {topic.title}
//                         </MenuItem>
//                     ))}
//                 </Select>
//                 <TextField label="Рівень"
//                            fullWidth
//                            value={editedEntity.level || '0'}
//                            onChange={(e) => handleChange('level', parseInt(e.target.value, 10))}
//                 />
//                 <TextField label="Відповідь"
//                            fullWidth
//                            value={editedEntity.answer}
//                            onChange={(e) => handleChange('answer', e.target.value)}
//                 />
//
//                 <Button onClick={handleSave} color="primary">
//                     Save
//                 </Button>
//             </Sheet>
//         </Modal>
//     );
// };
//
// export default EditModal;
