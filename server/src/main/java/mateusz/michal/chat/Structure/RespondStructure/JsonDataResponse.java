package mateusz.michal.chat.Structure.RespondStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonDataResponse implements IJsonResponse {
    IData data;
    IMetaData metaData;
}
