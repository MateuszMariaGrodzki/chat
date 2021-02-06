import BaseAPI from "./BaseAPI";
import { BaseResponse, ValidResponse } from "./types";

interface LoginRequest {
  name: string;
  password: string;
}

interface LoginResponse {
  token: string;
  name: string;
  email: string;
}

class LoginAPI extends BaseAPI {
  private static URL = "authenticate";

  public static validate(response: BaseResponse<LoginResponse>): response is ValidResponse<LoginResponse> {
    const missingField = ["token", "name", "email"].find((element) => !(element in response && typeof element === "string"));
    return !missingField;
  }

  public static async post(data: LoginRequest) {
    const response = await this.postRequest<LoginRequest, LoginResponse>(this.URL, data);
    return response;
  }
}

export default LoginAPI;
