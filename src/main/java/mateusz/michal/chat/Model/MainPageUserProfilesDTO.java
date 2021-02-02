package mateusz.michal.chat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPageUserProfilesDTO implements IData {
    List<UserNameAndSlug> data;
}
