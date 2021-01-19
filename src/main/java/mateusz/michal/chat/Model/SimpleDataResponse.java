package mateusz.michal.chat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleDataResponse implements IData {
    String info;
}
