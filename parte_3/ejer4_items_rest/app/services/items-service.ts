import type Item from "~/models/item";

const API_URL = "/api/items";

export async function loadItems(): Promise<Item[]> {
  try {
    const res = await fetch(`${API_URL}/`);
    const data = await res.json();
    return data;
  } catch (error) {
    console.error("Error loading items:", error);
    return [];
  }
}

export async function addItem(description: string, checked: boolean): Promise<Item | null> {
  try {
    const response = await fetch(`${API_URL}/`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ description, checked }),
    });
    const newItem: Item = await response.json();
    return newItem;
  } catch (error) {
    console.error("Error adding item:", error);
    return null;
  }
}

export async function removeItem(id: number): Promise<boolean> {
  try {
    await fetch(`${API_URL}/${id}`, {
      method: "DELETE",
    });
    return true;
  } catch (error) {
    console.error("Error removing item:", error);
    return false;
  }
}

export async function updateItem(id: number, description: string, checked: boolean): Promise<Item | null> {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ description, checked }),
    });
    const updatedItem: Item = await response.json();
    return updatedItem;
  } catch (error) {
    console.error("Error updating item:", error);
    return null;
  }
}
