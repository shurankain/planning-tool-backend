package com.shurankain.planning.app.security.service;

import com.shurankain.planning.app.security.model.User;
import com.shurankain.planning.app.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(userName)
                .orElse(User.builder()
                        .userName("empty")
                        .password("empty2")
                        .build());

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found!");
        }

        return new CustomUserDetails(user);
    }
}
