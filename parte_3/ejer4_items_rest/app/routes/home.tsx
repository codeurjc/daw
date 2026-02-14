import { useEffect, useRef, useState } from "react";
import ItemRow from "~/components/item-row";
import type Item from "~/models/item";
import * as itemsService from "~/services/items-service";

export default function Home() {
  const [items, setItems] = useState<Item[]>([]);

  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => { loadItems() }, []);

  async function loadItems() {
    const items = await itemsService.loadItems();
    setItems(items);
  }

  async function addItem(description: string) {
    const newItem = await itemsService.addItem(description, false);
    if (newItem) {
      setItems([...items, newItem]);
    }
  }

  async function removeItem(id: number) {
    const success = await itemsService.removeItem(id);
    if (success) {
      setItems(items.filter(item => item.id !== id));
    }
  }

  async function toggleItem(id: number) {
    const item = items.find(item => item.id === id);
    if (!item) return;

    const updatedItem = await itemsService.updateItem(
      id,
      item.description,
      !item.checked
    );
    if (updatedItem) {
      setItems(items.map(item => item.id === id ? updatedItem : item));
    }
  }

  async function handleAdd() {
    const value = inputRef.current?.value ?? "";
    if (value) {
      await addItem(value);
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

