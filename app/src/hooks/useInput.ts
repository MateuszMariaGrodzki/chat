import { useState } from "react";

export const useInput: (callback?: () => void) => [string, (e: React.ChangeEvent<HTMLInputElement>) => void] = (callback) => {
  const [value, setValue] = useState("");

  const handleValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
    if (callback) callback();
  };

  return [value, handleValueChange];
};
