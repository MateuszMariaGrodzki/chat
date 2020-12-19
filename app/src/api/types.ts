export enum REGISTER_API_ERROR {
  name_missing = "name_missing",
  email_missing = "email_missing",
  password_missing = "password_missing",
  name_occupied = "name_occupied",
  email_occupied = "email_occupied",
  email_incorrect = "email_incorrect",
  weak_password = "weak_password",
}

export enum GENERIC_ERROR {
  generic = "generic",
}

export type RegisterError = REGISTER_API_ERROR | GENERIC_ERROR;

export interface GenericError {
  errorCode: GENERIC_ERROR;
}

export namespace Register {
  export interface Request {
    name: string;
    email: string;
    password: string;
  }

  interface ValidResponse {
    errorCode: null;
    registered: true;
  }

  interface ErrorResponse {
    errorCode: RegisterError;
    registered: false;
  }
  export type Response = ValidResponse | ErrorResponse;
}
