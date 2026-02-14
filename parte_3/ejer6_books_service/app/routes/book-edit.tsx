import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { getBook, updateBook } from "~/services/books-service";
import type Book from "~/models/Book";

export default function BookEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState<Book | null>(null);
  const [loading, setLoading] = useState(true);

  const titleRef = useRef<HTMLInputElement>(null);
  const descriptionRef = useRef<HTMLInputElement>(null);

  async function loadBook() {
    if (id) {
      setLoading(true);
      const data = await getBook(id);
      setBook(data);
      setLoading(false);
    }
  }

  useEffect(() => { loadBook(); }, [id]);

  useEffect(() => {
    if (book) {
      if (titleRef.current) titleRef.current.value = book.title;
      if (descriptionRef.current) descriptionRef.current.value = book.description;
    }
  }, [book]);

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    const title = titleRef.current?.value ?? "";
    const description = descriptionRef.current?.value ?? "";
    if (title && id) {
      const updatedBook = await updateBook(id, title, description);
      if (updatedBook) {
        navigate(`/book/${id}`);
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
      <h1>Edit Book</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title: </label>
          <input type="text" ref={titleRef} required />
        </div>
        <div>
          <label>Description: </label>
          <input type="text" ref={descriptionRef} />
        </div>
        <button type="submit">Save</button>
        <button type="button" onClick={() => navigate(`/book/${id}`)}>Cancel</button>
      </form>
    </div>
  );
}
