import { Layout } from '../../components/layout/layout.component';
import { Students } from '../../pages/students';
import { createBrowserRouter } from 'react-router-dom';
import {Topics} from "../../pages/topics.tsx";
import {Tasks} from "../../pages/tasks.tsx";


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
			{
				path: 'topics',
				element: <Topics />,
			},
			{
				path: 'tasks',
				element: <Tasks />,
			}
		],
	},
]);

