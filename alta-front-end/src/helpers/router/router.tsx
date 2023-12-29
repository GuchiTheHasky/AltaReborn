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


// import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// //import { Layout } from '../../components/layout/layout.component';
// import { Students } from '../../pages/students';
// import { Topics } from '../../pages/topics.tsx';
// import { Tasks } from '../../pages/tasks.tsx';
//
// export const router = () => (
// 	<Router>
// 		<Routes>
// 			{/*<Route*/}
// 			{/*	path="/"*/}
// 			{/*	element={*/}
// 			{/*		<Layout>*/}
// 			{/*			<Students />*/}
// 			{/*		</Layout>*/}
// 			{/*	}*/}
// 			{/*/>*/}
// 			<Route path="students" element={<Students />} />
// 			<Route path="topics" element={<Topics />} />
// 			<Route path="tasks" element={<Tasks />} />
// 		</Routes>
// 	</Router>
// );
