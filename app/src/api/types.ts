type ErrorResponse = {
  apiError: boolean;
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
      errorCode: string;
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
