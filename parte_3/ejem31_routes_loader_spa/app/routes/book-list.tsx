import { Link, useLoaderData } from "react-router";
import { getBooks, type Book } from "~/services/books-service";
import type { Route } from "./+types/book-list";

export async function clientLoader({}: Route.ClientLoaderArgs) {
  const books = await getBooks();
  return { books };
}

export default function BookList() {
  
  const { books } = useLoaderData<typeof clientLoader>();

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
