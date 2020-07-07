package mateusz.michal.chat.Component;

import mateusz.michal.chat.Model.Message;
import mateusz.michal.chat.Model.Role;
import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataBaseLoader implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<Message> messageList = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        User user = User.builder().id(0).name("Maciek128").email("maciek128@gmail.com")
                .password("dzemmalinowy135")
                .messageList(messageList).roles(roles).build();
        userService.saveUser(user);
    }
}
