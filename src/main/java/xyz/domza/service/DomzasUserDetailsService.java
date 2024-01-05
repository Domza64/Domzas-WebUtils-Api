package xyz.domza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.domza.model.User;
import xyz.domza.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DomzasUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities;
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else{
            userName = user.getUsername();
            password = user.getPassword();
            authorities = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
        }
        return new org.springframework.security.core.userdetails.User(userName,password,authorities);
    }
}
