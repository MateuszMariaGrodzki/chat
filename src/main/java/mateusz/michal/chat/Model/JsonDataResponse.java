package mateusz.michal.chat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonDataResponse implements IJsonResponse {
    IData iData;
    IMetaData metaData;
}
