package mateusz.michal.chat.Service;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userRepository.findByName(name);
    ArrayList<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority("USER"));
    if(user == null){
        throw new UsernameNotFoundException("user_not_found");
    }
    return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                roles);
    }
}
