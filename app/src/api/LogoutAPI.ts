import BaseAPI from "./BaseAPI";
import { BaseResponse, ValidResponse } from "./types";

interface LogoutResponse {
  message: string;
}

class LogoutAPI extends BaseAPI {
  private static URL = "logout";

  public static validate(response: BaseResponse<LogoutResponse>): response is ValidResponse<LogoutResponse> {
    return Boolean("data" in response && response.data.message);
  }

  public static async get() {
    const response = await this.getRequest<LogoutResponse>(this.URL);
    return response;
  }
}

export default LogoutAPI;
