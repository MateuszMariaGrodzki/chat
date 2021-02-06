export interface APIError {
  status: number;
  code: string;
  title: string;
}

export type ValidResponse<ResponseData, ResponseMetaData = null> = {
  data: ResponseData;
  metaData: ResponseMetaData;
};

export type ErrorResponse = {
  errors: APIError[];
  metaData: null;
};

export type BaseResponse<ResponseData, ResponseMetaData = null> = ValidResponse<ResponseData, ResponseMetaData> | ErrorResponse;
