package mateusz.michal.chat.Service;

import mateusz.michal.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SlugService {

    @Autowired
    UserRepository userRepository;

    public String generateSlugFromName(String name) {
        StringBuilder result = new StringBuilder();
        name = name.toLowerCase();
        for (int i = 0; i < name.length(); ++i) {
            switch (name.charAt(i)) {
                case 'ą':
                    result.append('a');
                    break;
                case 'ć':
                    result.append('c');
                    break;
                case 'ę':
                    result.append('e');
                    break;
                case 'ł':
                    result.append('l');
                    break;
                case 'ń':
                    result.append('n');
                    break;
                case 'ó':
                    result.append('o');
                    break;
                case 'ś':
                    result.append('s');
                    break;
                case 'ź':
                    result.append('z');
                    break;
                case 'ż':
                    result.append('z');
                    break;
                case ' ':
                    result.append("-");
                default:
                    if (Character.isDigit(name.charAt(i)) || Character.isLowerCase(name.charAt(i)))
                        result.append(name.charAt(i));
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
