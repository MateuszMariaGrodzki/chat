package mateusz.michal.chat.Structure.RespondStructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyError {
    int status;
    IMyEnum code;
    String title;
}
