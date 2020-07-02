package mateusz.michal.chat.Service;

import mateusz.michal.chat.Model.Role;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private List<GrantedAuthority> getUserAuthorities(Set<Role> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        for(Role role : userRoles){
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> authorities = new ArrayList<>(roles);
        return authorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                true, true, true, true, authorities);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        List<GrantedAuthority> authorities = getUserAuthorities(user.getRoles());
        return buildUserForAuthentication(user,authorities);
    }
}
