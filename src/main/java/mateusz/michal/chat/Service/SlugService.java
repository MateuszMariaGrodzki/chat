package mateusz.michal.chat.Service;

import org.springframework.stereotype.Service;

@Service
public class SlugService {

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
}
