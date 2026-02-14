import { useState } from "react";

export default function Home() {

  const [titleClass, setTitleClass] = useState("red");
  const [textStyle, setTextStyle] = useState({ color: "blue" });

  function  handleClick() {
    setTitleClass("blue");
    setTextStyle({ color: "red" });
  }

  return (
    <>
      <h1 className={titleClass}>Title!</h1>

      <p style={textStyle}>Some text</p>

      <button onClick={handleClick}>Change Colors</button>
    </>  
  );

}