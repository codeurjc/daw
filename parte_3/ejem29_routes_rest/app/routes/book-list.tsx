import { useEffect, useState } from "react";
import { Link } from "react-router";
import { getBooks, type Book } from "~/services/books-service";

export default function BookList() {
  
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(false);
  
  async function loadBooks() {    
    setLoading(true);    
    setBooks(await getBooks());
    setLoading(false);
  }

  useEffect (() => { loadBooks() },[]);

  return (
    <div>
      <h2>BOOKS</h2>
      {loading && <p>Loading...</p>}
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            <Link to={`/book/${book.id}`}>
              {book.id} - {book.title}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
