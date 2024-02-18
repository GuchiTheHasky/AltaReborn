import {Button as MaterialButton} from '@mui/material';
import {ButtonHTMLAttributes, FC} from 'react';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    label: string;
    color: 'yellow' | 'green';
}

export const Button: FC<ButtonProps> = ({label, onClick, className, color}) => {
    return (
        <MaterialButton
            variant="contained"
            className={`w-full ${className} ${bgColor(color)}`}
            onClick={onClick}
        >
            {label}
        </MaterialButton>
    );
};


function bgColor(color: 'yellow' | 'green') {
    if (color === 'yellow') {
        return 'bg-[#fcc44d] hover:bg-[#C87124]';
    } else {
        return 'bg-[#79aa2d] hover:bg-[#4B8B44]';
    }
}
