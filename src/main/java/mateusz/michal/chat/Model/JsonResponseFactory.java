package mateusz.michal.chat.Model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonFactory {

    public IJsonResponse createResponse(ResponseEnum responseEnum,
                                        List<MyError> errors,
                                        IData data,
                                        IMetaData metaData){
        switch (responseEnum) {
            case DATA:
                return new JsonDataResponse(data, metaData);
            case ERROR:
                return new JsonErrorResponse(errors, metaData);
        }
        throw new UnsupportedOperationException();
    }
}
