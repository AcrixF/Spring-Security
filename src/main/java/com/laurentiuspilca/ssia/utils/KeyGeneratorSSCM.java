package com.laurentiuspilca.ssia.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.security.Principal;

public class KeyGeneratorSSCM {

    private Principal principal;
    private Authentication authentication;

    public void stringKeyGenerator() {
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String salt = keyGenerator.generateKey();
    }

    public void bytesKeyGenerator() {
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();
        byte [] key = keyGenerator.generateKey();
        int keyLength = keyGenerator.getKeyLength();
    }
}
