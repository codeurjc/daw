import { Outlet, useNavigation } from "react-router";
import "./home.css";

export default function Home() {

  const navigation = useNavigation();
  
  const isLoading = navigation.state === "loading";

  return (
    <div>
      <h1>Library {isLoading && <span className="spinner"></span>}</h1>
      <div style={{ opacity: isLoading ? 0.5 : 1 }}>
        <Outlet />
      </div>
    </div>
  );
}