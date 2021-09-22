package mateusz.michal.chat.DatabaseLoader.Service;

import mateusz.michal.chat.DatabaseLoader.Model.DatabaseLoaderErrorCode;
import mateusz.michal.chat.Structure.RespondStructure.MyError;
import mateusz.michal.chat.User.Model.User;
import mateusz.michal.chat.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseLoaderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<MyError> populateDatabase() {
        List<MyError> errors = new ArrayList<>();
        try {
            loadUsersIntoDatabase();
        } catch (IOException exception) {
            MyError error = new MyError(400,
                DatabaseLoaderErrorCode.FILE_NOT_FOUND,
                "File not found in resources firectory");
            errors.add(error);
        } catch (NumberFormatException numberException) {
            MyError error = new MyError(400,
                DatabaseLoaderErrorCode.BAD_FILE_STRUCTURE,
                "File has bad structure");
            errors.add(error);
        }
        return errors;
    }

    private void loadUsersIntoDatabase() throws IOException, NumberFormatException{
        Resource resource = new ClassPathResource("databasefiles/users.txt");
        File file = resource.getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String fileLine = bufferedReader.readLine();
        fileLine = bufferedReader.readLine();
        while((fileLine = bufferedReader.readLine()) != null){
            String[] userData = fileLine.split("[|]");
            User user = User.builder()
                    .id(Integer.parseInt(userData[0]))
                    .email(userData[1])
                    .name(userData[2])
                    .password(bCryptPasswordEncoder.encode(userData[3]))
                    .slug(userData[4]).build();
            userRepository.save(user);
        }
    }
}
