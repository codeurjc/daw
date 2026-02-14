import { useRef, useState } from "react";
import ItemRow from "~/components/item-row";
import type Item from "~/models/item";

export default function Home() {
  const [items, setItems] = useState<Item[]>([]);
  const [nextId, setNextId] = useState(1);

  const inputRef = useRef<HTMLInputElement>(null);

  function addItem(description: string) {
    const newItem: Item = { id: nextId, description, checked: false };
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
        <ItemRow
          key={item.id}
          item={item}
          onToggle={toggleItem}
          onRemove={removeItem}
        />
      ))}

      <hr />
      <input type="text" ref={inputRef} />
      <button onClick={handleAdd}>Add</button>
    </>
  );
}

