import { useRef, useState } from "react";

export default function Home() {

  const [items, setItems] = useState<string[]>([]);

  const inputRef = useRef<HTMLInputElement>(null);

  function handleClick() {
    const item = inputRef.current?.value;
    if(item){
      setItems([...items, inputRef.current?.value ?? ""]);
    }
    inputRef.current!.value = "";
  }

  return (
    <>
      <input type="text" ref={inputRef} />
      <button onClick={handleClick}>Add</button>

      <ul>
        {items.map(item => <li key={item}>{item}</li>)}
      </ul>
    </>
  );
}

