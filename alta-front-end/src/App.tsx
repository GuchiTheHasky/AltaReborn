
import { RouterProvider } from 'react-router-dom';
import {router} from './helpers/router/router';
import './index.css';
import {AppProvider} from "./api/context/appContext.tsx";

function App() {
	return (
		<>
			<AppProvider>
				<RouterProvider router={router}/>
			</AppProvider>
		</>
	);
}

export default App;
