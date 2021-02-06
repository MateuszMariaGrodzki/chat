import BaseAPI from "./BaseAPI";
import { BaseResponse, ValidResponse } from "./types";

interface LoggedUserResponse {
  name: string;
  email: string;
}

interface NoUserResponse {
  name: null;
  email: null;
}

type UserResponse = LoggedUserResponse | NoUserResponse;

class UserAPI extends BaseAPI {
  private static URL = "user";

  public static validate(response: BaseResponse<UserResponse>): response is ValidResponse<UserResponse> {
    return "data" in response && ["name", "email"].every((field) => field in response.data);
  }

  public static async get() {
    const response = await this.getRequest<UserResponse | NoUserResponse>(this.URL);
    return response;
  }
}

export default UserAPI;
