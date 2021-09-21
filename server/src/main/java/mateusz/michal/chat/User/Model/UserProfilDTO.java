package mateusz.michal.chat.User.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mateusz.michal.chat.Structure.RespondStructure.IData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilDTO implements IData {
    String name;
    String email;
}
