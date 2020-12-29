import { exhaustive } from "../utils/ehxaustive";
import { REGISTER_API_ERROR, GENERIC_ERROR, LOGIN_API_ERROR } from "./types";

export const getErrorText = (error: REGISTER_API_ERROR | LOGIN_API_ERROR | GENERIC_ERROR | null) => {
  switch (error) {
    case REGISTER_API_ERROR.EMAIL_INCORRECT:
      return "Email is incorrect";
    case REGISTER_API_ERROR.EMAIL_MISSING:
      return "Email is missing";
    case REGISTER_API_ERROR.EMAIL_OCCUPIED:
      return "Email already registered";
    case REGISTER_API_ERROR.NAME_MISSING:
    case LOGIN_API_ERROR.USERNAME_MISSING:
      return "Name missing";
    case REGISTER_API_ERROR.NAME_OCCUPIED:
      return "Name already taken";
    case LOGIN_API_ERROR.PASSWORD_MISSING:
    case REGISTER_API_ERROR.PASSWORD_MISSING:
      return "Password missing";
    case REGISTER_API_ERROR.WEAK_PASSWORD:
      return "Password too weak";
    case LOGIN_API_ERROR.BAD_CREDENTIALS:
      return "Login or password incorrect";
    case GENERIC_ERROR.GENERIC:
    case null:
      return "Unknown error. Try again later!";
    default:
      exhaustive(error);
  }
};
