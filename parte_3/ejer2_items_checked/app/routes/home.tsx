import { useRef, useState } from "react";

interface Item {
  id: number;
  description: string;
  checked: boolean;
}

export default function Home() {

  const [items, setItems] = useState<Item[]>([]);
  const [nextId, setNextId] = useState(1);

  const inputRef = useRef<HTMLInputElement>(null);

  function addItem(description: string) {
    const newItem = { id: nextId, description, checked: false };
    setItems([...items, newItem]);
    setNextId(nextId + 1);
  }

  function removeItem(id: number) {
    setItems(items.filter(item => item.id !== id));
  }

  function toggleItem(id: number) {
    setItems(items.map(item => 
      item.id === id ? { ...item, checked: !item.checked } : item
    ));
  }

  function handleAdd() {
    const value = inputRef.current?.value ?? "";
    if (value) {
      addItem(value);
      inputRef.current!.value = "";
    }
  }

  return (
    <>
      <h1>A checklist</h1>

      {items.map(item => (
        <div key={item.id}>
          <input 
            type="checkbox" 
            checked={item.checked}
            onChange={() => toggleItem(item.id)}
          />
          <span style={{ textDecoration: item.checked ? 'line-through' : 'none' }}>
            {item.description}
          </span>
          <button onClick={() => removeItem(item.id)}>Delete</button>
        </div>
      ))}

      <hr />
      <input type="text" ref={inputRef} />
      <button onClick={handleAdd}>Add</button>
    </>
  );
}

