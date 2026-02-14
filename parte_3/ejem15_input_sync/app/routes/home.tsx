import { useState, type ChangeEvent } from "react";

export default function Home() {

  const [name, setName] = useState("Anybody");

  function handleClick() {
    setName("John");
  }

  function onChange(e: ChangeEvent<HTMLInputElement>) {
    setName(e.target.value);
  }

  return (
    <>
      <input type="text" value={name} onChange={onChange}/>

      <h1>Hello {name}!</h1>

      <button onClick={handleClick}>Hello John</button >
    </>  
  );
}
