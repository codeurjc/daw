import { useNavigate, useParams } from "react-router";
import { useBooksStore } from "~/stores/books-store";

export default function BookDetail() {

  const { id } = useParams();
  const navigate = useNavigate();
  const { getBook } = useBooksStore();

  const book = getBook(id!);

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
