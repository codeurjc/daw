import { useState } from "react";

export default function Home() {

  const [name, setName] = useState("Anybody");

  function handleClick() {
    setName("John");
  }

  return (
    <>
      <h1>Hello {name}!</h1>

      <button onClick={handleClick}>Hello John</button>
    </>  
  );
}
