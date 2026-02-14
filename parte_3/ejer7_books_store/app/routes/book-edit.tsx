import { useEffect, useRef } from "react";
import { useNavigate, useParams } from "react-router";
import { useBooksStore } from "~/stores/books-store";

export default function BookEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { books, loadBooks, updateBook } = useBooksStore();

  const titleRef = useRef<HTMLInputElement>(null);
  const descriptionRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (books.length === 0) {
      loadBooks();
    }
  }, [books.length, loadBooks]);

  if (!id) {
    return <p>Missing book id</p>;
  }

  const book = books.find((item) => item.id === id);

  useEffect(() => {
    if (book) {
      if (titleRef.current) titleRef.current.value = book.title;
      if (descriptionRef.current) descriptionRef.current.value = book.description;
    }
  }, [book]);

  async function handleSubmit(e: React.SubmitEvent) {
    e.preventDefault();
    const title = titleRef.current?.value ?? "";
    const description = descriptionRef.current?.value ?? "";
    if (title) {
      await updateBook(id!, title, description);
      navigate(`/book/${id}`);
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
