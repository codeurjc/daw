import Header from "~/components/header";

export default function Home() {

  const title = "Welcome to Home";

  function handleChange(visible: boolean) {
    console.log("Header visible: "+ visible);
  }

  return (
    <>
      <Header title={title} onChange={handleChange} />
      <p>Main content</p>
    </>
  );
}