
import { RouterProvider } from 'react-router-dom';
import {router} from './helpers/router/router';
import './index.css';

function App() {
	return (
		<>
			<RouterProvider router={router} />
		</>
	);
}

export default App;
