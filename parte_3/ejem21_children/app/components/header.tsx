import { useState, type ReactNode } from "react";

interface HeaderProps {
  children: ReactNode;
}

export default function Header({ children }: HeaderProps) {

  return (
    <div>
      {children}
    </div>
  );
}
