import { Button as MaterialButton } from '@mui/material';

export const GreenButton = ({ label }: { label: string }) => {
	return (
		<MaterialButton variant="contained" className="w-full" style={{ backgroundColor: '#79aa2d' }}>
			{label}
		</MaterialButton>
	);
};
