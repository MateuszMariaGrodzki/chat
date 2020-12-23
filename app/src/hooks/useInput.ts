import { useState } from "react";

export const useInput: () => [string, (e: React.ChangeEvent<HTMLInputElement>) => void] = () => {
  const [value, setValue] = useState("");

  const handleValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  };

  return [value, handleValueChange];
};
