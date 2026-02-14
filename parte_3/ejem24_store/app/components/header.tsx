import { useTitleStore } from "~/stores/title-store";

export default function Header() {

  const { title } = useTitleStore();

  return (
    <h1>{title}</h1>
  );
}
