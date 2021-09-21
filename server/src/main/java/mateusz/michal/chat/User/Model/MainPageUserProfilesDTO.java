package mateusz.michal.chat.User.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mateusz.michal.chat.Structure.RespondStructure.IData;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPageUserProfilesDTO implements IData {
    List<UserNameAndSlug> users;
}
