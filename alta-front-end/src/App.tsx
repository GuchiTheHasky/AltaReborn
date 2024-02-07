import {RouterProvider} from 'react-router-dom';
import {router} from './helpers/router/router';
import './index.css';
import {DataProviderContextProvider} from "./context/data-provider.context.tsx";
import { RedirectToSignIn, SignedIn, SignedOut } from "@clerk/clerk-react"


function App() {
    return (
        <>
            <SignedOut>
                <RedirectToSignIn />
            </SignedOut>
            <SignedIn>
                <DataProviderContextProvider>
                    <RouterProvider router={router}/>
                </DataProviderContextProvider>
            </SignedIn>
        </>
    );
}

export default App;
