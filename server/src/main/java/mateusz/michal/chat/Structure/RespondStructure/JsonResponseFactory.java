package mateusz.michal.chat.Structure.RespondStructure;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonResponseFactory {

    public static IJsonResponse createResponse(List<MyError> errors, IMetaData metaData){
        return new JsonErrorResponse(errors,metaData);
    }

    public static IJsonResponse createResponse(List<MyError> errors){
        return new JsonErrorResponse(errors,null);
    }

    public static IJsonResponse createResponse(IData iData, IMetaData metaData){
        return new JsonDataResponse(iData, metaData);
    }

    public static IJsonResponse createResponse(IData iData){
        return new JsonDataResponse(iData, null);
    }
}
