import React, { createContext, ReactNode } from 'react';

const AppContext = createContext<ContextType | undefined>(undefined);

interface ContextType {}

export const AppProvider: React.FC<{ children: ReactNode }> = ({ children }) => {

    return (
        <AppContext.Provider value={{}}>
            {children}
        </AppContext.Provider>
    );
};
