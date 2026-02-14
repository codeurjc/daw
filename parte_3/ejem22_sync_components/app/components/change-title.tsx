interface ChangeTitleProps {
  setTitle: (title: string) => void;
}

export default function ChangeTitle({ setTitle }: ChangeTitleProps) {
  
  return (
    <button onClick={() => setTitle("New Title")}>Set New Title</button>
  );
}
