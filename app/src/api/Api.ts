import Axios from "axios";

import { POST, POSTRequest } from "./types";

const apiURL = "http://localhost:8080/api";

class API {
  public static post: POSTRequest = (url, data) => {
    return Axios.post<POST[typeof url]["response"]>(`${apiURL}/${url}`, data)
      .then((response) => {
        if (response.status !== 200) {
          console.log("not 200!", response.status);
          return {
            apiError: true,
          };
        }
        if (response.data) {
          console.log("Received data", response.data);
          return response.data;
        }
      })
      .catch((error) => {
        console.error("Caught error!", error);
        return {
          apiError: true,
        };
      });
  };

  public static register() {
    // TODO
  }
}

export default API;
