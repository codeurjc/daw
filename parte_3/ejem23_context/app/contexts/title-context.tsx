import { createContext, useState, type ReactNode } from "react";

interface TitleContextType {
  title: string;
  setTitle: (title: string) => void;
}

export const TitleContext = createContext<TitleContextType>({} as TitleContextType);

export function TitleProvider({ children }: { children: ReactNode }) {

  const [title, setTitle] = useState("Welcome to Home");

  return (
    <TitleContext.Provider value={{ title, setTitle }}>
      {children}
    </TitleContext.Provider>
  );
}