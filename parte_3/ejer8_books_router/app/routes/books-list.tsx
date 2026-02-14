import { Link, useNavigate } from "react-router";
import { getBooks } from "~/services/books-service";
import type { Route } from "./+types/books-list";

export async function clientLoader({}: Route.ClientLoaderArgs) {
  return await getBooks();
}

export default function BooksList({ loaderData }: Route.ComponentProps) {
  
  const books = loaderData;
  
  const navigate = useNavigate();

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
