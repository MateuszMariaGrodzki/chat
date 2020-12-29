interface User {
  name: string;
  email: string;
}

export interface UserContextValue {
  /**
   * `null` - unlogged user;
   * `undefined` - unknown (unfetched) user
   */
  user: User | null | undefined;
  setUser: (user: User | null) => void;
}