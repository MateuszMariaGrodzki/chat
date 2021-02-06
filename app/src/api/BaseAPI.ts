import Axios, { AxiosRequestConfig, AxiosResponse } from "axios";

import { BaseResponse, ValidResponse, ErrorResponse } from "./types";

abstract class BaseAPI {
  private static apiURL = "http://localhost:8080/api";

  private static axiosConfig: AxiosRequestConfig = {
    validateStatus: (status) => status < 500,
    withCredentials: true,
  };

  private static getGenericErrorResponse(responseStatus: number): ErrorResponse {
    return { metaData: null, errors: [{ status: responseStatus, code: "generic", title: "Generic error" }] };
  }

  public static async getRequest<ResponseData, ResponseMetaData = null>(url: string) {
    const response = await Axios.get<BaseResponse<ResponseData, ResponseMetaData>>(`${this.apiURL}/${url}`, this.axiosConfig);
    let responseData: BaseResponse<ResponseData, ResponseMetaData>;
    if (response.data) {
      responseData = response.data;
    } else {
      responseData = this.getGenericErrorResponse(response.status);
    }
    return responseData;
  }

  public static async postRequest<RequestData, ResponseData, ResponseMetaData = null>(url: string, data: RequestData) {
    const response = await Axios.post<BaseResponse<ResponseData, ResponseMetaData>>(`${this.apiURL}/${url}`, data, this.axiosConfig);
    let responseData: BaseResponse<ResponseData, ResponseMetaData>;
    if (response.data) {
      responseData = response.data;
    } else {
      responseData = this.getGenericErrorResponse(response.status);
    }
    return responseData;
  }
}

export default BaseAPI;
