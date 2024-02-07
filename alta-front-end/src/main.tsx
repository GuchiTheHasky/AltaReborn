import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';
import {ClerkProvider} from '@clerk/clerk-react'


const queryClient = new QueryClient();
const PUBLISHABLE_KEY = import.meta.env.VITE_CLERK_PUBLISHABLE_KEY;
if (!PUBLISHABLE_KEY) {
    throw new Error("Missing Publishable Key")
}

async function enableMocking() {
    if (import.meta.env.VITE_API_MOCKED !== 'true') {
        return;
    }

    const {worker} = await import('./mocks/browser');

    // `worker.start()` returns a Promise that resolves
    // once the Service Worker is up and ready to intercept requests.
    return worker.start();
}

enableMocking().then(() => {
    ReactDOM.createRoot(document.getElementById('root')!).render(
        <React.StrictMode>
            <QueryClientProvider client={queryClient}>
                <ClerkProvider publishableKey={PUBLISHABLE_KEY}>
                    <App />
                </ClerkProvider>
            </QueryClientProvider>
        </React.StrictMode>
    );
});
