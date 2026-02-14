import { useTitleStore } from "~/stores/title-store";

export default function ChangeTitle() {
  
  const { setTitle } = useTitleStore();

  return (
    <button onClick={() => setTitle("New Title")}>Set New Title</button>
  );
}
