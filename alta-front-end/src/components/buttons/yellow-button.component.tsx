import { Button as MaterialButton } from '@mui/material';
import { FC } from 'react';

interface YellowButtonProps {
    label: string;
    onClick?: () => void;
}

export const YellowButton: FC<YellowButtonProps> = ({ label, onClick }: YellowButtonProps) => {
    return (
        <MaterialButton
            variant="contained"
            className="w-full"
            style={{ backgroundColor: '#fcc44d' }}
            onClick={onClick}
        >
            {label}
        </MaterialButton>
    );
};