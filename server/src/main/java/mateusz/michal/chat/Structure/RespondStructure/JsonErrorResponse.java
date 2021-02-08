package mateusz.michal.chat.Structure.RespondStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonErrorResponse implements IJsonResponse {
    List<MyError> errors;
    IMetaData metaData;
}