package com.quynhptt.java5.service;

import com.quynhptt.java5.config.CustomUserDetails;
import com.quynhptt.java5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Hibernate.initialize(user.getRoles());
//        return User.withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRoles().stream().map(Role::getName).toArray(String[]::new))
//                .build();

        return new CustomUserDetails(
                user.getId(), // Store userId
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toList())
        );
    }

    public com.quynhptt.java5.entity.User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
