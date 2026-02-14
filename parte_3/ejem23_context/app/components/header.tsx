import { useContext } from "react";
import { TitleContext } from "~/contexts/title-context";

export default function Header() {

  const { title } = useContext(TitleContext);

  return (
    <h1>{title}</h1>
  );
}
