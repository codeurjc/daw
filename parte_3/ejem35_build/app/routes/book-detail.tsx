import { useNavigate } from "react-router";
import { getBook, removeBook } from "~/services/books-service";
import type { Route } from "./+types/book-detail";

export async function clientLoader({ params }: Route.ClientLoaderArgs) {
  return await getBook(params.id!);
}

export default function BookDetail({ loaderData }: Route.ComponentProps) {
  
  const book = loaderData;
  const navigate = useNavigate();

  async function handleDelete() {
    if (book?.id) {
      const success = await removeBook(book.id);
      if (success) {
        navigate("/");
      }
    }
  }

  if (!book) {
    return (
      <div>
        <p>Book not found</p>
        <button onClick={() => navigate("/")}>Back</button>
      </div>
    );
  }

  return (
    <div>
      <h1>{book.title}</h1>
      <p>{book.description}</p>
      <button onClick={() => navigate(`/book/${book.id}/edit`)}>Edit</button>
      <button onClick={handleDelete}>Delete</button>
      <button onClick={() => navigate("/")}>Back</button>
    </div>
  );
}
