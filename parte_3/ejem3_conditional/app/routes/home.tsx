export default function Home() {

  const logged = true;

  return (
    <>
      <div>Company Logo</div>
      {logged ?
        <>
          <h1>Welcome back!</h1>
          <p>You have successfully logged in.</p>
        </>
        :
        <p>Please log in</p>
      }
      <div>Footer</div>
    </>
  );
}

