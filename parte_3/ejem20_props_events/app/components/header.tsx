import { useState } from "react";

interface HeaderProps {
  title: string;
  onChange: (visible: boolean) => void;
}

export default function Header({ title, onChange }: HeaderProps) {

  const [visible, setVisible] = useState(true);

  function handleClick() {
    const newVisible = !visible;    
    setVisible(newVisible);
    if(onChange) {
      onChange(newVisible);
    }
  }

  return (
    <>
      {visible && <h1>{title}</h1>}
      <button onClick={handleClick}>Hide/Show</button>
    </>
  );
}
