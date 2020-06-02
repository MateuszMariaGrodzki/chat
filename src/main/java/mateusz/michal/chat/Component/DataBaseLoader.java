package mateusz.michal.chat.Component;

import mateusz.michal.chat.Model.Message;
import mateusz.michal.chat.Model.Role;
import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Repository.UserRepository;
import mateusz.michal.chat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataBaseLoader implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<Message> messageList = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        User user = new User(0,"Maciek128","maciek128@gmail.com",
                "DzemMalinowy135",messageList,roles);
        userService.saveUser(user);
    }
}
