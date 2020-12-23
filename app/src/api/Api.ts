import Axios from "axios";

import { GENERIC_ERROR, Register } from "./types";

// TODO: move to app config
const apiURL = "http://localhost:8080/api";

class API {
  public static post<T, K>(url: string, data: T) {
    return Axios.post<K>(`${apiURL}/${url}`, data)
      .then((response) => {
        if (response.status !== 200 || !response.data) {
          throw Error(`Server response status ${response.status}`);
        }
        return response.data;
      })
      .catch((error: unknown) => {
        console.error(error);
        return {
          errorCode: GENERIC_ERROR.GENERIC,
        };
      });
  }

  public static register(data: Register.Request) {
    return this.post<Register.Request, Register.Response>("register", data);
  }
}

export default API;
