import type Item from "~/models/item";

interface ItemRowProps {
  item: Item;
  onToggle: (id: number) => void;
  onRemove: (id: number) => void;
}

export default function ItemRow({ item, onToggle, onRemove }: ItemRowProps) {
  return (
    <div>
      <input
        type="checkbox"
        checked={item.checked}
        onChange={() => onToggle(item.id)}
      />
      <span style={{ textDecoration: item.checked ? "line-through" : "none" }}>
        {item.description}
      </span>
      <button onClick={() => onRemove(item.id)}>Delete</button>
    </div>
  );
}