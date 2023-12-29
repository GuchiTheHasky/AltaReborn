import { Button as MaterialButton } from '@mui/material';
import { FC } from 'react';

interface GreenButtonProps {
	label: string;
	onClick?: () => void;
}

export const GreenButton: FC<GreenButtonProps> = ({ label, onClick }: GreenButtonProps) => {
	return (
		<MaterialButton
			variant="contained"
			className="w-full"
			style={{ backgroundColor: '#79aa2d' }}
			onClick={onClick}
		>
			{label}
		</MaterialButton>
	);
};


// import { Button as MaterialButton } from '@mui/material';
//
// export const GreenButton = ({ label }: { label: string }) => {
// 	return (
// 		<MaterialButton variant="contained" className="w-full" style={{ backgroundColor: '#79aa2d' }}>
// 			{label}
// 		</MaterialButton>
// 	);
// };
