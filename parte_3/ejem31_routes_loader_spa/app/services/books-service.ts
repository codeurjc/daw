export interface Book {
  id: string;
  title: string;
  description: string;
}

export async function getBooks(): Promise<Book[]> {

  try {

    const response = await fetch('https://openlibrary.org/search.json?title=react&fields=title,key&limit=10');
    const data = await response.json();

    if (data.docs) {
      return data.docs.map((item: any) => ({
        id: item.key.split("/").pop(),
        title: item.title
      }));
    }

    return [];

  } catch (error) {
    console.error(error);
    return [];
  }
}

export async function getBook(id: string): Promise<Book | undefined> {

  const response = await fetch(`https://openlibrary.org/works/${id}.json`);
  const data = await response.json();

  const description = typeof data.description === 'string'
    ? data.description
    : data.description?.value || "No hay descripción disponible";

  return {
    id, title: data.title, description
  };

}
