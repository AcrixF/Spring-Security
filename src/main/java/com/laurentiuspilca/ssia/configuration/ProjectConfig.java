package com.laurentiuspilca.ssia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers("/hello").hasRole("ADMIN")
                .mvcMatchers("/ciao").hasRole("MANAGER")
                .anyRequest().authenticated();
                //.anyRequest().permitAll();

    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user_1 = User.withUsername("Neoa")
                .password("12345")
                .roles("ADMIN")
                .build();

        UserDetails user_2 = User.withUsername("AcrixF")
                .password("12345")
                .roles("MANAGER")
                .build();

        userDetailsManager.createUser(user_1);
        userDetailsManager.createUser(user_2);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
