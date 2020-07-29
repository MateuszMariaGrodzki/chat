type ErrorResponse = {
  apiError: boolean;
};

export type POSTRequest = <T extends keyof POST>(url: T, data: POST[T]["request"]) => Promise<POST[T]["response"] | ErrorResponse>;

export enum REGISTER_ERROR {
  name_missing = "name_missing",
  email_missing = "email_missing",
  password_missing = "password_missing",
  name_occupied = "name_occupied",
  email_occupied = "email_occupied",
  email_incorrect = "email_incorrect",
  weak_password = "weak_password",
}

export interface POST {
  register: {
    request: {
      name: string;
      email: string;
      password: string;
    };
    response: {
      errorCode: REGISTER_ERROR;
      registered: "true" | "false";
    };
  };
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
