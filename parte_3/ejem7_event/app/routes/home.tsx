export default function Home() {

  function handleClick() {
    alert("Hello John");
  }

  return (
    <>
      <button onClick={handleClick}>
        Hello John
      </button >
    </>  
  );
}
