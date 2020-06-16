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
                .regexMatchers(".*/[us|uk|ca]+/[en|fr].*")
                    .authenticated()
                .anyRequest().hasAuthority("premium");

        http.csrf().disable();

    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user_1 = User.withUsername("Neoa")
                .password("12345")
                .authorities("read")
                .build();

        UserDetails user_2 = User.withUsername("AcrixF")
                .password("12345")
                .authorities("read", "premium")
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
