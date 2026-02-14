import { useEffect } from "react";
import { useNavigate, useParams } from "react-router";
import { useBooksStore } from "~/stores/books-store";

export default function BookDetail() {

  const { id } = useParams();
  const navigate = useNavigate();
  const { books, loadBooks, removeBook } = useBooksStore();

  useEffect(() => {
    if (books.length === 0) {
      loadBooks();
    }
  }, [books.length, loadBooks]);

  if (!id) {
    return <p>Missing book id</p>;
  }

  const book = books.find((item) => item.id === id);

  async function handleDelete() {
    await removeBook(id!);
    navigate("/");
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
      <button onClick={() => navigate(`/book/${id}/edit`)}>Edit</button>
      <button onClick={handleDelete}>Delete</button>
      <button onClick={() => navigate("/")}>Back</button>
    </div>
  );
}
