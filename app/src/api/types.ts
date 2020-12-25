export const enum REGISTER_API_ERROR {
  NAME_MISSING = "NAME_MISSING",
  EMAIL_MISSING = "EMAIL_MISSING",
  PASSWORD_MISSING = "PASSWORD_MISSING",
  NAME_OCCUPIED = "NAME_OCCUPIED",
  EMAIL_OCCUPIED = "EMAIL_OCCUPIED",
  EMAIL_INCORRECT = "EMAIL_INCORRECT",
  WEAK_PASSWORD = "WEAK_PASSWORD",
}

export const enum LOGIN_API_ERROR {
  USERNAME_MISSING = "USERNAME_MISSING",
  PASSWORD_MISSING = "PASSWORD_MISSING",
  BAD_CREDENTIALS = "BAD_CREDENTIALS",
}

export enum GENERIC_ERROR {
  GENERIC = "GENERIC",
}

export type RegisterError = REGISTER_API_ERROR | GENERIC_ERROR;
export type LoginError = LOGIN_API_ERROR | GENERIC_ERROR;

export interface GenericError {
  errorCode: GENERIC_ERROR;
}

export namespace Register {
  export interface Request {
    name: string;
    email: string;
    password: string;
  }

  export interface ValidResponse {
    errorCode: null;
    registered: true;
  }

  interface ErrorResponse {
    errorCode: REGISTER_API_ERROR;
    registered: false;
  }

  export type Response = ValidResponse | ErrorResponse | GenericError;
}

export function isValidRegisterResponse(response: Register.Response): response is Register.ValidResponse {
  return response.errorCode === null && "registered" in response && typeof response.registered === "boolean";
}

export namespace Login {
  export interface Request {
    name: string;
    password: string;
  }

  export interface ValidResponse {
    errorCode: null;
    token: string;
    name: string;
    email: string;
  }

  interface ErrorResponse {
    errorCode: LoginError;
    token: null;
    name: null;
    email: null;
  }

  export type Response = ValidResponse | ErrorResponse | GenericError;
}

export function isValidLoginResponse(response: Login.Response): response is Login.ValidResponse {
  const missingElement = ["token", "name", "email"].find((element) => !(element in response && typeof element === "string"));
  return response.errorCode === null && !missingElement;
}
