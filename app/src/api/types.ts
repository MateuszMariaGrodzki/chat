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

type ErrorResponse = {
  errorCode: GENERIC_ERROR;
};

export type POSTRequest = <T extends keyof POST>(url: T, data: POST[T]["request"]) => Promise<POST[T]["response"] | ErrorResponse>;

export interface POST {
  register: {
    request: {
      name: string;
      email: string;
      password: string;
    };
    response: {
      errorCode: Maybe<REGISTER_API_ERROR>;
      registered: boolean;
    };
  };
  /**
   * example below; TODO: modify
   */
  login: {
    request: {
      first_name: string;
      last_name: string;
    };
    response: {
      loginErorCode: string;
    };
  };
}
