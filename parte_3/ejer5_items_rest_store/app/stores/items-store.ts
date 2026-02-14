import { create } from "zustand";
import type Item from "~/models/item";

const API_URL = "/api/items";

interface ItemsState {
  items: Item[];
  loadItems: () => Promise<void>;
  addItem: (description: string) => Promise<void>;
  removeItem: (id: number) => Promise<void>;
  toggleItem: (id: number) => Promise<void>;
}

export const useItemsStore = create<ItemsState>((set, get) => ({
  items: [],

  loadItems: async () => {
    try {
      const res = await fetch(`${API_URL}/`);
      const data = await res.json();
      set({ items: data });
    } catch (err) {
      console.error("Error loading items:", err);
    }
  },

  addItem: async (description: string) => {
    try {
      const response = await fetch(`${API_URL}/`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ description, checked: false }),
      });
      const newItem: Item = await response.json();
      set({ items: [...get().items, newItem] });
    } catch (err) {
      console.error("Error adding item:", err);
    }
  },

  removeItem: async (id: number) => {
    try {
      await fetch(`${API_URL}/${id}`, {
        method: "DELETE",
      });
      set({ items: get().items.filter(item => item.id !== id) });
    } catch (err) {
      console.error("Error removing item:", err);
    }
  },

  toggleItem: async (id: number) => {
    const item = get().items.find(item => item.id === id);
    if (!item) return;

    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          description: item.description,
          checked: !item.checked
        }),
      });
      const updatedItem: Item = await response.json();
      set({ items: get().items.map(item => item.id === id ? updatedItem : item) });
    } catch (err) {
      console.error("Error toggling item:", err);
    }
  },
}));
