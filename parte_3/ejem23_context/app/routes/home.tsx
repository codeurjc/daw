import ChangeTitle from "~/components/change-title";
import Header from "~/components/header";
import { TitleProvider } from "~/contexts/title-context";

export default function Home() {
  return (
    <TitleProvider>
      <Header />
      <p>Main content</p>
      <ChangeTitle />
    </TitleProvider>
  );
}