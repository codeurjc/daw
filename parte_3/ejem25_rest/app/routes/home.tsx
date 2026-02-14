import { useRef, useState } from "react";

export default function Home() {

  const [titles, setTitles] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);

  const titleRef = useRef<HTMLInputElement>(null);

  async function handleSubmit(event: React.SubmitEvent) {
  
    event.preventDefault();

    setTitles([]);
    setLoading(true);

    const searchTerm = titleRef.current?.value ?? "";

    const url = `https://openlibrary.org/search.json?title=${searchTerm}`;

    try {
      const response = await fetch(url);
      const data = await response.json();

      if (data.docs) {
        const bookTitles = data.docs.map((item: any) => item.title);
        setTitles(bookTitles);
      }
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }

    event.target.reset();
  };

  return (
    <div>
      <h1>Open Library</h1>

      <form onSubmit={handleSubmit}>
        <input type="text" ref={titleRef} disabled={loading} />
        <button type="submit" disabled={loading}>Buscar</button>
      </form>

      {loading && <p>Cargando...</p>}

      {!loading && titles.length === 0 && <p>No se encontraron resultados</p>}

      {!loading && titles.map((title, index) => (
        <p key={index}>{title}</p>
      ))}
    </div>
  );
}