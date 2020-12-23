import { exhaustive } from "../utils/ehxaustive";
import { REGISTER_API_ERROR, GENERIC_ERROR } from "./types";

export const getErrorText = (error: REGISTER_API_ERROR | GENERIC_ERROR) => {
  switch (error) {
    case REGISTER_API_ERROR.EMAIL_INCORRECT:
      return "Email is incorrect";
    case REGISTER_API_ERROR.EMAIL_MISSING:
      return "Email is missing";
    case REGISTER_API_ERROR.EMAIL_OCCUPIED:
      return "Email already registered";
    case REGISTER_API_ERROR.NAME_MISSING:
      return "Name missing";
    case REGISTER_API_ERROR.NAME_OCCUPIED:
      return "Name already taken";
    case REGISTER_API_ERROR.PASSWORD_MISSING:
      return "Password missing";
    case REGISTER_API_ERROR.WEAK_PASSWORD:
      return "Password too weak";
    case GENERIC_ERROR.GENERIC:
      return "Unknown error. Try again later!";
    default:
      exhaustive(error);
  }
};
