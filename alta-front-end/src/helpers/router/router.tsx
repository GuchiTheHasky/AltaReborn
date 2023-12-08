import { Layout } from '../../components/layout/layout.component';
import { Students } from '../../pages/students';
import { createBrowserRouter } from 'react-router-dom';


export const router = createBrowserRouter([
	{
		path: '/',
		element: <Layout />,
		children: [
			{
        index: true,
				element: <Students />,
      },
			{
				path: 'students',
				element: <Students />,
			},
		],
	},
]);