import { useState } from "react";
import ChangeTitle from "~/components/change-title";
import Header from "~/components/header";

export default function Home() {

  const [title, setTitle] = useState("Welcome to Home");

  return (
    <>
      <Header title={title} />
      <p>Main content</p>
      <ChangeTitle setTitle={setTitle} />
    </>
  );
}