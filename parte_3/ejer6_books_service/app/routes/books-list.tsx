import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router";
import { getBooks } from "~/services/books-service";
import type Book from "~/models/Book";

export default function BooksList() {
  
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(false);
  
  const navigate = useNavigate();

  async function loadBooks() {
    setLoading(true);
    const data = await getBooks();
    setBooks(data);
    setLoading(false);
  }

  useEffect(() => { loadBooks(); }, []);

  return (
    <>
      <h2>Books</h2>
      {loading && <p>Loading...</p>}
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            <Link to={`/book/${book.id}`}>{book.title}</Link>
          </li>
        ))}
      </ul>
      <button onClick={() => navigate("/book-new")}>Add Book</button>
    </>
  );
}
