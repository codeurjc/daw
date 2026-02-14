import { useContext } from "react";
import { TitleContext } from "~/contexts/title-context";

export default function ChangeTitle() {
  
  const { setTitle } = useContext(TitleContext);

  return (
    <button onClick={() => setTitle("New Title")}>Set New Title</button>
  );
}
