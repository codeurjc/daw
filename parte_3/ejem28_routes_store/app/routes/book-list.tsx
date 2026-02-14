import { Link } from "react-router";
import { useBooksStore } from "~/stores/books-store";

export default function BookList() {
  
  const { books } = useBooksStore();

  return (
    <div>
      <h2>BOOKS</h2>
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
