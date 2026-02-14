import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { getBook, removeBook } from "~/services/books-service";
import type Book from "~/models/Book";

export default function BookDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState<Book | null>(null);
  const [loading, setLoading] = useState(true);

  async function loadBook() {
    if (id) {
      setLoading(true);
      const data = await getBook(id);
      setBook(data);
      setLoading(false);
    }
  }

  useEffect(() => { loadBook(); }, [id]);

  async function handleDelete() {
    if (id) {
      const success = await removeBook(id);
      if (success) {
        navigate("/");
      }
    }
  }

  if (!id) {
    return <p>Missing book id</p>;
  }

  if (loading) {
    return <p>Loading...</p>;
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
