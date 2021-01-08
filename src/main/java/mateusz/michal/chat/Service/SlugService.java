package mateusz.michal.chat.Service;

import mateusz.michal.chat.Repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Random;

@Service
public class SlugService {

    @Autowired
    UserRepository userRepository;

    public String generateSlugFromName(String name) {
        name = name.toLowerCase();
        name = StringUtils.stripAccents(name);
        StringBuilder result = new StringBuilder();
        for(char c : name.toCharArray()) {
            if(c == ' '){
                result.append('-');
            } else if( (c >= 48 && c <= 57) || (c >= 97 && c <= 122) ) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String generateUniqueSlug(String name){
        String convertedSlug = generateSlugFromName(name);

        while(userRepository.findBySlug(convertedSlug) != null){
            Random random = new Random();
            convertedSlug += random.nextInt(1000);
        }
        return convertedSlug;
    }
}
