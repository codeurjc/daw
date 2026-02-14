import { useRef, useState } from "react";

export default function Home() {

  const [name, setName] = useState("Anybody");

  const inputRef = useRef<HTMLInputElement>(null);

  function handleClick() {
    let input = inputRef.current;
    setName(input?.value ?? "");
    input!.value = "";
  }

  return (
    <>
      <input type="text" ref={inputRef}/>

      <h1>Hello {name}!</h1>

      <button onClick={handleClick}>Update title</button>
    </>  
  );
}
