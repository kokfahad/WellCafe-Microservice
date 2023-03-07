package com.fahad.microservice.jwt;

import com.fahad.microservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private com.fahad.microservice.model.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userRepository.findByEmail(username).get();

        if (!Objects.isNull(userDetail))
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("User not found !!");
    }

    public com.fahad.microservice.model.User getUserDetail(){
//        com.fahad.cafeteria.model.User user = userDetail;
        return userDetail;
    }
}
