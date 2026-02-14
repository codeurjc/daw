import { useNavigate, useLoaderData } from "react-router";
import { getBook } from "~/services/books-service";
import type { Route } from "./+types/book-detail";

export async function clientLoader({ params }: Route.ClientLoaderArgs) {
  const book = await getBook(params.id!);
  return { book };
}

export default function BookDetail() {
  const { book } = useLoaderData<typeof clientLoader>();
  const navigate = useNavigate();

  if (!book) {
    return <p>Book not found</p>;
  }

  return (
    <div>
      <h2>{book.title}</h2>
      <div>
        <label>Id: </label>{book.id}
      </div>
      <div>
        <label>Description: </label>{book.description}
      </div>
      <p>
        <button onClick={() => navigate("/")}>Back</button>
      </p>
    </div>
  );
}