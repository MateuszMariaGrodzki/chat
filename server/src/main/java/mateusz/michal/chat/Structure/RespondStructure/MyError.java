package mateusz.michal.chat.Structure.RespondStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mateusz.michal.chat.Structure.RespondStructure.IMyEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyError {
    int status;
    IMyEnum code;
    String title;
}
