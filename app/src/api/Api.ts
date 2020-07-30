import Axios from "axios";

import { POST, POSTRequest, GENERIC_ERROR } from "./types";

// TODO: move to app config
const apiURL = "http://localhost:8080/api";

class API {
  public static post: POSTRequest = (url, data) => {
    return Axios.post<POST[typeof url]["response"]>(`${apiURL}/${url}`, data)
      .then((response) => {
        if (response.status !== 200 || !response.data) {
          return {
            errorCode: GENERIC_ERROR.generic,
          };
        }
        return response.data;
      })
      .catch((error) => {
        console.error(error);
        return {
          errorCode: GENERIC_ERROR.generic,
        };
      });
  };

  public static register(data: POST["register"]["request"]) {
    return this.post("register", data);
  }
}

export default API;
