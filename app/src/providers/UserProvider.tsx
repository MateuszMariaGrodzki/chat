import React, { createContext, useCallback, useContext, useEffect, useState } from "react";

import API from "@api/Api";
import { isValidUserResponse, isValidLogoutResponse } from "@api/types";

import { UserContextValue } from "./types";

const defaultValue: UserContextValue = {
  user: undefined,
  setUser: (_user) => {},
  logout: () => {},
};

const UserContext = createContext(defaultValue);

export const UserContextProvider: React.FC = ({ children }) => {
  const [user, setUser] = useState<UserContextValue["user"]>(undefined);

  const fetchUser = useCallback(async () => {
    const fetchedUser = await API.getUser();
    if (!isValidUserResponse(fetchedUser)) {
      return;
    }
    if (fetchedUser.email && fetchedUser.name) {
      setUser({
        name: fetchedUser.name,
        email: fetchedUser.email,
      });
    } else {
      setUser(null);
    }
  }, []);

  const logout = useCallback(async () => {
    const response = await API.logout();
    if (isValidLogoutResponse(response)) {
      setUser(null);
    }
  }, []);

  useEffect(() => {
    fetchUser();
  }, []);

  return <UserContext.Provider value={{ user, setUser, logout }}>{children}</UserContext.Provider>;
};

export const useUserContext = () => useContext(UserContext);
