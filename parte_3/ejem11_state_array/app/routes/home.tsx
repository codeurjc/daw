import { useState } from "react";

export default function Home() {

  const [users, setUsers] = useState([
    { id: 1, name: "John", age: 23 },
    { id: 2, name: "Peter", age: 45 }]
  );

  function incrementAge(id: number) {
    //DON’T WORK: users.find(user => user.id === id)!.age++;
    setUsers(users.map(user => user.id === id ? { ...user, age: user.age + 1 } : user));
  }

  return (
    users.map(user => <div key={user.id}>
      <p>Name: {user.name}</p>
      <p>Age: {user.age}</p>
      <button onClick={() => incrementAge(user.id)}>Increment age</button>
    </div>)
  );
}