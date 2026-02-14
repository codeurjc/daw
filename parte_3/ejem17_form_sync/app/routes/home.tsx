import { useRef, useState, type ChangeEvent } from "react";

export default function Home() {
  interface User {
    name?: string;
    special?: boolean;
    gender?: string;
    country?: string;
  }

  const [user, setUser] = useState<User>();

  const formRef = useRef<HTMLFormElement>(null);

  function handleChangeName(event: ChangeEvent<HTMLInputElement>) {
    setUser({ ...user, name: event.target.value });
  }

  function handleChangeSpecial(event: ChangeEvent<HTMLInputElement>) {
    setUser({ ...user, special: event.target.checked });
  }

  function handleChangeGender(event: ChangeEvent<HTMLInputElement>) {
    setUser({ ...user, gender: event.target.value });
  }

  function handleChangeCountry(event: ChangeEvent<HTMLSelectElement>) {
    setUser({ ...user, country: event.target.value });
  }

  function handleReset() {
    formRef.current?.reset();
    setUser(undefined);
  }

  function handleRandomUser() {
    setUser({
      name: "John Doe", 
      special: Math.random() < 0.5, 
      gender: Math.random() < 0.5 ? "male" : "female", 
      country: ["spain", "usa", "france"][Math.floor(Math.random() * 3)]
    });
  }

  return (
    <>

      <form ref={formRef}>

        <h1>Create user</h1>

        <label htmlFor="name">Name: </label>
        <input name="name" value={user?.name ?? ""} type="text" onChange={handleChangeName} />

        <br />

        <label htmlFor="special">Special: </label>
        <input name="special" checked={user?.special ?? false} type="checkbox" onChange={handleChangeSpecial} />

        <br />

        <label htmlFor="gender">Gender: </label>
        <input name="gender" value="male" checked={user?.gender === "male"} type="radio" onChange={handleChangeGender} /> Male
        <input name="gender" value="female" checked={user?.gender === "female"} type="radio" onChange={handleChangeGender} /> Female

        <br />

        <label htmlFor="country">Country: </label>
        <select name="country" onChange={handleChangeCountry}>
          <option value="" disabled selected hidden>Select...</option>
          <option value="spain" selected={user?.country === "spain"}>Spain</option>
          <option value="usa" selected={user?.country === "usa"}>USA</option>
          <option value="france" selected={user?.country === "france"}>France</option>
        </select>

        <br />

        <button type="button" onClick={handleReset}>
          Reset form
        </button >

        <button type="button" onClick={handleRandomUser}>
          Random user
        </button >
      </form >

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
