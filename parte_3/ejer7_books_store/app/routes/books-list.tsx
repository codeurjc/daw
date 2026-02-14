import { useEffect } from "react";
import { Link, useNavigate } from "react-router";
import { useBooksStore } from "~/stores/books-store";

export default function BooksList() {
  const { books, loadBooks } = useBooksStore();
  const navigate = useNavigate();

  useEffect(() => { loadBooks(); });

  return (
    <>
      <h2>Books</h2>
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
