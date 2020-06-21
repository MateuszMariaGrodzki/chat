package mateusz.michal.chat.Service;

import mateusz.michal.chat.Model.Role;
import mateusz.michal.chat.Model.User;
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
public class MyUserDetailsService {


    private List<GrantedAuthority> getUserAuthorities(Set<Role> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        for(Role role : userRoles){
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> authorities = new ArrayList<>(roles);
        return authorities;
    }
}
