import BaseAPI from "./BaseAPI";
import { BaseResponse, ValidResponse } from "./types";

interface UsersResponse {
  // TODO: remove nesting after backend bugfix
  data: ListedUser[];
}

class UsersAPI extends BaseAPI {
  private static URL = "users";

  public static validate(response: BaseResponse<UsersResponse>): response is ValidResponse<UsersResponse> {
    return "data" in response && Array.isArray(response.data.data);
  }

  public static async get() {
    const response = await this.getRequest<UsersResponse>(this.URL);
    return response;
  }
}

export default UsersAPI;
