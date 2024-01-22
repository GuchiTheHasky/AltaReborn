import {RouterProvider} from 'react-router-dom';
import {router} from './helpers/router/router';
import './index.css';
import {DataProviderContextProvider} from "./context/data-provider.context.tsx";

function App() {
    return (
        <DataProviderContextProvider>
            <RouterProvider router={router}/>
        </DataProviderContextProvider>
    );
}

export default App;
