import React, { createContext, useCallback, useContext, useEffect, useState } from "react";

import API from "@api/Api";
import { isValidLogoutResponse } from "@api/types";
import UserAPI from "@api/UserAPI";

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
    const response = await UserAPI.get();
    if (!UserAPI.validate(response)) {
      // TODO: error -> something went wrong fetching user
      setUser(null);
      return;
    }
    const { name, email } = response.data;
    if (name && email) {
      setUser({
        name,
        email,
      });
    } else {
      setUser(null);
    }
  }, []);

  const logout = useCallback(async () => {
    const response = await API.logout();
    // @ts-ignore
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
