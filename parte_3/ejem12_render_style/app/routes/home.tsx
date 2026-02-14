export default function Home() {

  return (
    <>
      { /*DON'T WORK: <h1 class="red">Title!</h1>*/ }
      <h1 className="red">Title!</h1>

      { /*DON'T WORK: <p style="color: blue">Some text</p>*/ }
      <p style={{ color: "blue" }}>Some text</p>
    </>  
  );

}