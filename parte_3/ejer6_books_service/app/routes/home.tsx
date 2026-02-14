import { Outlet } from "react-router";

export default function Home() {
  return (
    <div>
      <h1>Library</h1>
      <Outlet />
    </div>
  );
}

