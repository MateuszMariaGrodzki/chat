import Axios, { AxiosRequestConfig, AxiosResponse } from "axios";

import { GENERIC_ERROR, GenericError, BaseResponse, ValidResponse, ErrorResponse, APIError } from "./types";

abstract class BaseAPI {
  private static apiURL = "http://localhost:8080/api";

  private static axiosConfig: AxiosRequestConfig = {
    validateStatus: (status) => status < 500,
    withCredentials: true,
  };

  private static isStatusOk(status: number) {
    return status >= 200 && status < 400;
  }

  private static isValidDataResponse<ValidResponseType>(response: AxiosResponse): response is AxiosResponse<ValidResponseType> {
    return this.isStatusOk(response.status) && response.data.hasOwnProperty("data");
  }

  private static isValidErrorResponse(response: AxiosResponse): response is AxiosResponse<ErrorResponse> {
    return !this.isStatusOk(response.status) && Array.isArray(response.data.errors) && response.data.errors.length > 0;
  }

  private static getGenericErrorResponse(): ErrorResponse {
    return { metaData: null, errors: [{ status: 0, code: "generic", title: "Generic error" }] };
  }

  public static async getRequest<ResponseData, ResponseMetaData = null>(url: string) {
    const response = await Axios.get<BaseResponse<ResponseData, ResponseMetaData>>(`${this.apiURL}/${url}`, this.axiosConfig);
    let responseData: BaseResponse<ResponseData, ResponseMetaData>;
    console.log(response);
    if (this.isValidDataResponse<ValidResponse<ResponseData, ResponseMetaData>>(response) || this.isValidErrorResponse(response)) {
      responseData = response.data;
    } else {
      responseData = this.getGenericErrorResponse();
    }
    return responseData;
  }

  // TODO: handle post requests
  public static postRequest<Request, Response>(url: string, data: Request) {
    // return Axios.post<Response>(`${this.apiURL}/${url}`, data, this.axiosConfig).then((response) => {
    //   console.log(response);
    //   const isStatusOk = response.status >= 200 && response.status < 400;
    //   if (!response.data && !isStatusOk) {
    //     throw Error(`Server response status ${response.status}`);
    //   }
    //   return response.data;
    // });
    // .catch((_error) => {
    //   return {
    //     errorCode: GENERIC_ERROR.GENERIC,
    //   } as GenericError;
    // });
  }
}

export default BaseAPI;
