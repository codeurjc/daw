import { useEffect, useRef } from "react";
import ItemRow from "~/components/item-row";
import { useItemsStore } from "~/stores/items-store";

export default function Home() {
  
  const { items, loadItems, addItem, removeItem, toggleItem } = useItemsStore();

  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => { loadItems() }, []);

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

