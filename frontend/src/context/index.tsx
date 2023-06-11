import React from "react";
import AuthProvider from "./Auth";

function Contexts({ children }: { children: React.ReactNode }) {
  return <AuthProvider>{children}</AuthProvider>;
}

export default Contexts;
