import { create } from "zustand";

interface TitlesState {
  titles: string[];
  loading: boolean;
  searchTitles: (searchTerm: string) => Promise<void>;
}

export const useTitlesStore = create<TitlesState>((set) => ({
  
  titles: [],
  loading: false,

  searchTitles: async (searchTerm: string) => {
    set({ titles: [], loading: true });

    const url = `https://openlibrary.org/search.json?title=${searchTerm}`;

    try {
      const response = await fetch(url);
      const data = await response.json();

      if (data.docs) {
        const bookTitles = data.docs.map((item: any) => item.title);
        set({ titles: bookTitles });
      }
    } catch (error) {
      console.error(error);
    } finally {
      set({ loading: false });
    }
  },
}));
