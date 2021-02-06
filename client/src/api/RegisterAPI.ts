import BaseAPI from "./BaseAPI";
import { BaseResponse, ValidResponse } from "./types";

interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

interface RegisterResponse {
  message: string;
}

class RegisterAPI extends BaseAPI {
  private static URL = "register";

  public static validate(response: BaseResponse<RegisterResponse>): response is ValidResponse<RegisterResponse> {
    return Boolean("data" in response && response.data.message);
  }

  public static async post(data: RegisterRequest) {
    const response = await this.postRequest<RegisterRequest, RegisterResponse>(this.URL, data);
    return response;
  }
}

export default RegisterAPI;
