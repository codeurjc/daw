import { useRef, useEffect } from "react";
import { useNavigate } from "react-router";
import { getBook, updateBook } from "~/services/books-service";
import type { Route } from "./+types/book-edit";

export async function clientLoader({ params }: Route.ClientLoaderArgs) {
  return await getBook(params.id!);
}

export default function BookEdit({ loaderData }: Route.ComponentProps) {
  
  const book = loaderData;
  const navigate = useNavigate();

  const titleRef = useRef<HTMLInputElement>(null);
  const descriptionRef = useRef<HTMLInputElement>(null);

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
    if (title && book?.id) {
      const updatedBook = await updateBook(book.id, title, description);
      if (updatedBook) {
        navigate(`/book/${book.id}`);
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
        <button type="button" onClick={() => navigate(`/book/${book.id}`)}>Cancel</button>
      </form>
    </div>
  );
}
