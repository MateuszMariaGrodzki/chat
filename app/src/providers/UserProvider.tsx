import React, { createContext, useContext, useEffect, useState } from "react";

import { UserContextValue } from "./types";

const defaultValue: UserContextValue = {
  user: undefined,
  setUser: (_user) => {},
};

const UserContext = createContext(defaultValue);

export const UserContextProvider: React.FC = ({children}) => {
  const [user, setUser] = useState<UserContextValue["user"]>(undefined);
  useEffect(() => {
    setTimeout(() => {
      setUser(null);
    }, 5_000)
  }, []);
  return <UserContext.Provider value={{user, setUser}}>
    {children}
  </UserContext.Provider>
}

export const useUserContext = () => useContext(UserContext);