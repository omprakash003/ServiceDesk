package com.om.servicedesk;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256);

        SecretKey key = keyGen.generateKey();

        String secret = Base64.getEncoder()
                .encodeToString(key.getEncoded());

        System.out.println(secret);
    }
}
