import { useRef } from "react";
import { useNavigate } from "react-router";
import { addBook } from "~/services/books-service";

export default function BookNew() {
  const navigate = useNavigate();

  const titleRef = useRef<HTMLInputElement>(null);
  const descriptionRef = useRef<HTMLInputElement>(null);

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    const title = titleRef.current?.value ?? "";
    const description = descriptionRef.current?.value ?? "";
    if (title) {
      const newBook = await addBook(title, description);
      if (newBook) {
        navigate("/");
      }
    }
  }

  return (
    <div>
      <h1>New Book</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title: </label>
          <input type="text" ref={titleRef} required />
        </div>
        <div>
          <label>Description: </label>
          <input type="text" ref={descriptionRef} />
        </div>
        <button type="submit">Create</button>
        <button type="button" onClick={() => navigate("/")}>Cancel</button>
      </form>
    </div>
  );
}
