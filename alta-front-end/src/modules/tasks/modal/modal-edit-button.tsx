import React, {useEffect, useState} from 'react';
import Modal from '@mui/joy/Modal';
import Sheet from '@mui/joy/Sheet';
import TextField from '@mui/material/TextField';
import {Button} from "@mui/joy";
import {api} from "../../../core/api.ts";
import {MenuItem, Select} from "@mui/material";
import {useAppContext} from "../../../api/context/appContext.tsx";

interface Entity {
    id: number;
    title: string;
    level: number;
    answer: string;
}

interface EditModalProps {
    open: boolean;
    onClose: () => void;
    entity: Entity;
}

const EditModal: React.FC<EditModalProps> = ({open, onClose, entity}) => {
    const [editedEntity, setEditedEntity] = useState<Entity>({...entity});
    const [selectedTheme, setSelectedTheme] = useState<string>('');
    const {topics} = useAppContext();
    const themeOptions = [
        'Числові множини', 'Подільність', 'Задачі на рух, відсотки...',
        'Дробові вирази', 'Степінь', 'Арифметичний корінь',
        'Логарифм', 'Лінійні рівняння', 'Квадратні рівняння',
        'Дробово-раціональні рівняння', 'Рівняння з модулем', 'Ірраціональні рівняння',
        'Показникові рівняння', 'Логарифмічні рівняння', 'Системи рівнянь',
        'Метод інтервалів', 'Нерівності з модулем', 'Прогресія',
        'Ірраціональні нерівності', 'Показникові нерівності', 'Логарифмічні нерівності',
        'Тригонометрія', 'Похідна', 'Лінійні нерівності',
        'Квадратні нерівності', 'Тригонометрія.Формули', 'Тригометричні рівняння',
        'Первісна', 'Тригометричні нерівності', 'Функції, графіки',
        'Комбінаторика', 'Ймовірність', 'Стереометрія',
        'Обернені тригонометричні функціі', 'Раціональні числа, розкладання на множники', 'Вектори, Координати',
        'Різне', 'Задачі з параметром', 'Модуль',
        'Статистика', 'Планіметрія'];


    useEffect(() => {
        console.log(' 1 useEffect, entity:', entity);
        console.log(' 1 useEffect, editedEntity:', editedEntity);
        console.log(' 1 useEffect, selectedTheme:', selectedTheme);
        console.log(' 1 useEffect, entity.title', entity.title);
        setEditedEntity({...entity});
        setSelectedTheme(entity.title);
    }, [entity]);

    const handleSave = async () => {
        try {
            console.log(' 2 handleSave, entity:', entity);
            console.log(' 2 handleSave, editedEntity:', editedEntity);
            console.log(' 2 handleSave, selectedTheme:', selectedTheme);
            console.log(' 2 handleSave, entity.title', entity.title);
            console.log(' 2 handleSave, entity.id', entity.id);
            console.log(' 2 handleSave, selectedTopic', topics.find((topic) => topic.title === selectedTheme));

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
        console.log(' 3 handleChange, entity:', entity);
        console.log(' 3 handleChange, editedEntity:', editedEntity);
        console.log(' 3 handleChange, selectedTheme:', selectedTheme);
        console.log(' 3 handleChange, entity.title', entity.title);
        console.log(' 3 handleChange, field:', field);
        setEditedEntity((prevEntity) => ({
            ...prevEntity,
            [field]: value,
        }));

        // Оновлення вибраної теми
        if (field === 'title') {
            setSelectedTheme(value as string);
        }
    };

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
                {/*<TextField label="Тема"*/}
                {/*           fullWidth*/}
                {/*           value={editedEntity.title}*/}
                {/*           onChange={(e) => handleChange('title', e.target.value)}*/}
                {/*/>*/}
                <Select
                    label="Тема"
                    fullWidth
                    value={selectedTheme}
                    onChange={(e) => handleChange('title', e.target.value)}
                >
                    {themeOptions.map((theme) => (
                        <MenuItem key={theme} value={theme}>
                            {theme}
                        </MenuItem>
                    ))}
                </Select>
                <TextField label="Рівень"
                           fullWidth
                           value={editedEntity.level}
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
