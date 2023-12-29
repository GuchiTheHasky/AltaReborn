import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';
//import {ClerkProvider} from "@clerk/nextjs";

const queryClient = new QueryClient();

async function enableMocking() {
    if (import.meta.env.VITE_API_MOCKED !== 'true') {
        return;
    }

    const {worker} = await import('./mocks/browser');

    // `worker.start()` returns a Promise that resolves
    // once the Service Worker is up and ready to intercept requests.
    return worker.start();
}

// const PUBLISHABLE_KEY = import.meta.env.local.NEXT_PUBLIC_CLERK_PUBLISHABLE_KEY
//
// if (!PUBLISHABLE_KEY) {
//     throw new Error("Missing Publishable Key")
// }

enableMocking().then(() => {
    ReactDOM.createRoot(document.getElementById('root')!).render(
        <React.StrictMode>
            {/*<ClerkProvider >*/}
                <QueryClientProvider client={queryClient}>
                    <App/>
                </QueryClientProvider>
           {/* </ClerkProvider>*/}
        </React.StrictMode>
    );
});
