package com.laurentiuspilca.ssia.utils;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class Encoders {

    public void noOpPasswordEncoder() {
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
    }

    public void standardPasswordEncoder() {
        PasswordEncoder stdP = new StandardPasswordEncoder();
        PasswordEncoder stdP1 = new StandardPasswordEncoder("Secret");
    }

    public void pbkdf2PasswordEncoder() {
        PasswordEncoder pbkdf = new Pbkdf2PasswordEncoder();
        PasswordEncoder pbkdf1 = new Pbkdf2PasswordEncoder("Secret");
        PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder("Secret", 18500, 256);
    }

}
