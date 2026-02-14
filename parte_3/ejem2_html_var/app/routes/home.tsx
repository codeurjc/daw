export default function Home() {

  const logged = true;
  let message;

  if(logged) {
    message = <>
        <h1>Welcome back!</h1>
        <p>You have successfully logged in.</p>
    </>;
  } else {
    message = <p>Please log in</p>;
  }
  
  return (
    <>
      <div>Company Logo</div>
      {message}    
      <div>Footer</div>
    </>  
  );
}
