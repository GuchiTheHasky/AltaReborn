
import { RouterProvider } from 'react-router-dom';
import {router} from './helpers/router/router';
//import { SignInButton, SignedIn, SignedOut, UserButton } from "@clerk/clerk-react"
import './index.css';
import {AppProvider} from "./api/context/appContext.tsx";

function App() {
	return (
		<>
			<AppProvider>
				<RouterProvider router={router}/>
			</AppProvider>
			{/*<div>*/}
			{/*	<SignedOut>*/}
			{/*		<SignInButton/>*/}
			{/*	</SignedOut>*/}
			{/*	<SignedIn>*/}
			{/*		<UserButton afterSignOutUrl="/"/>*/}
			{/*	</SignedIn>*/}
			{/*</div>*/}
		</>
	);
}

export default App;
