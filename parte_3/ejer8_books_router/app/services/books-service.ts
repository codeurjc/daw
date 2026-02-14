import type Book from "~/models/Book";

const API_URL = "/api/books";

export async function getBooks(): Promise<Book[]> {
  try {
    const res = await fetch(`${API_URL}/`);
    const data = await res.json();
    return data;
  } catch (err) {
    console.error("Error loading books:", err);
    return [];
  }
}

export async function getBook(id: string): Promise<Book | null> {
  try {
    const res = await fetch(`${API_URL}/${id}`);
    if (!res.ok) return null;
    const data = await res.json();
    return data;
  } catch (err) {
    console.error("Error loading book:", err);
    return null;
  }
}

export async function addBook(title: string, description: string): Promise<Book | null> {
  try {
    const response = await fetch(`${API_URL}/`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, description }),
    });
    const newBook: Book = await response.json();
    return newBook;
  } catch (err) {
    console.error("Error adding book:", err);
    return null;
  }
}

export async function removeBook(id: string): Promise<boolean> {
  try {
    await fetch(`${API_URL}/${id}`, {
      method: "DELETE",
    });
    return true;
  } catch (err) {
    console.error("Error removing book:", err);
    return false;
  }
}

export async function updateBook(id: string, title: string, description: string): Promise<Book | null> {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, description }),
    });
    const updatedBook: Book = await response.json();
    return updatedBook;
  } catch (err) {
    console.error("Error updating book:", err);
    return null;
  }
}
