import { create } from 'zustand';

interface TitleStore {
  title: string;
  setTitle: (title: string) => void;
}

export const useTitleStore = create<TitleStore>((set) => ({
  title: 'Welcome to Home',
  setTitle: (title) => set({ title }),
}));
