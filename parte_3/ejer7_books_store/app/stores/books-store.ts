import { create } from "zustand";
import type Book from "~/models/book";

const API_URL = "/api/books";

interface BooksState {
  books: Book[];
  loadBooks: () => Promise<void>;
  addBook: (title: string, description: string) => Promise<void>;
  removeBook: (id: string) => Promise<void>;
  updateBook: (id: string, title: string, description: string) => Promise<void>;
}

export const useBooksStore = create<BooksState>((set, get) => ({
  books: [],

  loadBooks: async () => {
    try {
      const res = await fetch(`${API_URL}/`);
      const data = await res.json();
      set({ books: data });
    } catch (err) {
      console.error("Error loading books:", err);
    }
  },

  addBook: async (title: string, description: string) => {
    try {
      const response = await fetch(`${API_URL}/`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description }),
      });
      const newBook: Book = await response.json();
      set({ books: [...get().books, newBook] });
    } catch (err) {
      console.error("Error adding book:", err);
    }
  },

  removeBook: async (id: string) => {
    try {
      await fetch(`${API_URL}/${id}`, {
        method: "DELETE",
      });
      set({ books: get().books.filter(book => book.id !== id) });
    } catch (err) {
      console.error("Error removing book:", err);
    }
  },

  updateBook: async (id: string, title: string, description: string) => {
    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description }),
      });
      const updatedBook: Book = await response.json();
      set({ books: get().books.map(book => book.id === id ? updatedBook : book) });
    } catch (err) {
      console.error("Error updating book:", err);
    }
  },
}));
