package mateusz.michal.chat.Structure.RespondStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mateusz.michal.chat.Structure.RespondStructure.IData;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleDataResponse implements IData {
    String message;
}
