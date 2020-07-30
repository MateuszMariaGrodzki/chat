import { REGISTER_API_ERROR, GENERIC_ERROR } from "./types";

const registerApiFeedback: { [key in keyof typeof REGISTER_API_ERROR]: string } = {
  email_incorrect: "Email is incorrect",
  email_missing: "Email missing",
  email_occupied: "Email already registered",
  name_occupied: "Name already taken",
  name_missing: "Name missing",
  password_missing: "Password missing",
  weak_password: "Password too weak",
};

const genericFeedback: { [key in keyof typeof GENERIC_ERROR]: string } = {
  generic: "Unknown error. Try again later!",
};

export const registerFeedback = {
  ...registerApiFeedback,
  ...genericFeedback,
};
