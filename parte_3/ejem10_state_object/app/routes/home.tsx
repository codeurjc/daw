import { useState } from "react";

export default function Home() {

  const [user, setUser] = useState(
    { name: "John", age: 23 }
  );

  function handleClick() {
    //DON'T WORK: user.age++;
    setUser({ ...user, age: user.age + 1 });
  }

  return (
    <>
      <p>Name: {user.name}</p>
      <p>Age: {user.age}</p>
      <button onClick={handleClick}>Increment age</button>
    </>
  );
}