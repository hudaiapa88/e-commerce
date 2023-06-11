import { createContext, useContext, useState } from "react";
import { User } from "../types/Account";

interface ContextType {
  currentUser: User;
  setCurrentUser: React.Dispatch<React.SetStateAction<User>>;
}

export const AuthContext = createContext<ContextType | null>(null);

export default function AuthProvider({ children }) {
  const [currentUser, setCurrentUser] = useState(null);
  const data = {
    currentUser,
    setCurrentUser,
  };
  return <AuthContext.Provider value={data}>{children}</AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext);
