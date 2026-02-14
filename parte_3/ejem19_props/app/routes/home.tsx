import Header from "~/components/header";

export default function Home() {

  const title = "Welcome to Home";

  return (
    <>
      <Header title={title} />
      <p>Main content</p>
    </>
  );
}