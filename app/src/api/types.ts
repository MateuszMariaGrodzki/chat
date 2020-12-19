export const enum REGISTER_API_ERROR {
  NAME_MISSING = "NAME_MISSING",
  EMAIL_MISSING = "EMAIL_MISSING",
  PASSWORD_MISSING = "PASSWORD_MISSING",
  NAME_OCCUPIED = "NAME_OCCUPIED",
  EMAIL_OCCUPIED = "EMAIL_OCCUPIED",
  EMAIL_INCORRECT = "EMAIL_INCORRECT",
  WEAK_PASSWORD = "WEAK_PASSWORD",
}

export enum GENERIC_ERROR {
  GENERIC = "GENERIC",
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
