import Axios, { AxiosRequestConfig } from "axios";

import { GENERIC_ERROR, Register, Login, GenericError, User, Logout } from "./types";

class API {
  private static apiURL = "http://localhost:8080/api";
  private static axiosConfig: AxiosRequestConfig = {
    validateStatus: (status) => status < 500,
    withCredentials: true,
  };

  public static get<Response>(url: string) {
    return Axios.get<Response>(`${this.apiURL}/${url}`, this.axiosConfig)
      .then((response) => {
        const isStatusOk = response.status >= 200 && response.status < 400;
        if (!response.data && !isStatusOk) {
          throw Error(`Server response status ${response.status}`);
        }
        return response.data;
      })
      .catch((_error) => {
        return {
          errorCode: GENERIC_ERROR.GENERIC,
        } as GenericError;
      });
  }

  public static post<Request, Response>(url: string, data: Request) {
    return Axios.post<Response>(`${this.apiURL}/${url}`, data, this.axiosConfig)
      .then((response) => {
        const isStatusOk = response.status >= 200 && response.status < 400;
        if (!response.data && !isStatusOk) {
          throw Error(`Server response status ${response.status}`);
        }
        return response.data;
      })
      .catch((_error) => {
        return {
          errorCode: GENERIC_ERROR.GENERIC,
        } as GenericError;
      });
  }

  public static register(data: Register.Request) {
    return this.post<Register.Request, Register.Response>("register", data);
  }

  public static login(data: Login.Request) {
    return this.post<Login.Request, Login.Response>("authenticate", data);
  }

  public static logout() {
    return this.get<Logout.Response>("logout");
  }

  public static getUser() {
    return this.get<User.Response>("user");
  }
}

export default API;
