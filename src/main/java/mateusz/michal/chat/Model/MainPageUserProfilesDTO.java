package mateusz.michal.chat.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageUserProfilesDTO {
    String errorCode;
    List<UserNameAndSlug> data;
}
