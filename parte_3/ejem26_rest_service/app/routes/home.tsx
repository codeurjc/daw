import { useRef, useState } from "react";
import { searchTitles } from "~/services/titles-service";

export default function Home() {
  
  const [titles, setTitles] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);
  
  const titleRef = useRef<HTMLInputElement>(null);

  async function handleSubmit(event: React.SubmitEvent) {
  
    event.preventDefault();

    const searchTerm = titleRef.current?.value ?? "";

    setLoading(true);
    
    const results = await searchTitles(searchTerm);
    
    setTitles(results);
    setLoading(false);

    event.target.reset();
  }

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