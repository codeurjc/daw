import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { getBook, type Book } from "~/services/books-service";

export default function BookDetail() {

  const { id } = useParams();
  const navigate = useNavigate();

  const [book, setBook] = useState<Book>();
  const [loading, setLoading] = useState(false);
  
  async function loadBook() {    
    setLoading(true);    
    setBook(await getBook(id!));
    setLoading(false);
  }
  
  useEffect (() => { loadBook() },[]);

  if(loading) {
    return <p>Loading...</p>;
  }

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