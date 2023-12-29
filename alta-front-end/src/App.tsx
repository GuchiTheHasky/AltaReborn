
import { RouterProvider } from 'react-router-dom';
import {router} from './helpers/router/router';
//import { SignInButton, SignedIn, SignedOut, UserButton } from "@clerk/clerk-react"
import './index.css';

function App() {
	return (
		<>
			<RouterProvider router={router}/>
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
