package com.laurentiuspilca.ssia.services;

import com.laurentiuspilca.ssia.entities.User;
import com.laurentiuspilca.ssia.model.CustomUserDetail;
import com.laurentiuspilca.ssia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> usernameNotFoundException = () -> new UsernameNotFoundException("Problem during Authentication");

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(usernameNotFoundException);

        return new CustomUserDetail(user);

    }
}
