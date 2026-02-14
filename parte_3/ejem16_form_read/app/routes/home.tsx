import { useState, type SubmitEvent } from "react";

export default function Home() {
  interface User {
    name?: string;
    special?: boolean;
    gender?: string;
    country?: string;
  }

  const [user, setUser] = useState<User>();

  function handleSubmit(event: SubmitEvent) {
    event.preventDefault();

    const formData = new FormData(event.target);

    setUser({
      name: formData.get("name") as string,
      special: formData.get("special") === "on",
      gender: formData.get("gender") as string,
      country: formData.get("country") as string
    });

    event.target.reset();
  }

  return (
    <>
      <form onSubmit={handleSubmit}>

        <h1>Create user</h1>

        <label htmlFor="name">Name: </label>
        <input name="name" type="text" />

        <br />

        <label htmlFor="special">Special: </label>
        <input name="special" type="checkbox" />

        <br />

        <label htmlFor="gender">Gender: </label>
        <input name="gender" value="male" type="radio" /> Male
        <input name="gender" value="female" type="radio" /> Female

        <br />

        <label htmlFor="country">Country: </label>
        <select name="country">
          <option value="" disabled selected hidden>Select...</option>
          <option value="spain">Spain</option>
          <option value="usa">USA</option>
          <option value="france">France</option>
        </select>

        <br />

        <button type="submit">
          Process form
        </button >
      </form>

      {user && (
        <p> Name: {user.name ? user.name : "Not specified"}<br />
          Special: {user.special ? "Yes" : "No"}<br />
          Gender: {user.gender ? user.gender : "Not specified"}<br />
          Country: {user.country ? user.country : "Not specified"}
        </p >)
      }
    </>
  );
}
