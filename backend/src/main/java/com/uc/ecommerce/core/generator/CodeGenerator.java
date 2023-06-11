package com.uc.ecommerce.core.generator;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class CodeGenerator {
    public String generate(){
        SecureRandom prng = null;
        try {
            prng = SecureRandom.getInstance("SHA1PRNG");
            String randomNum =  Integer.valueOf(prng.nextInt()).toString();
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] result =  sha.digest(randomNum.getBytes());
             return hexEncode(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
     private String hexEncode(byte[] input){
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
        for (int idx = 0; idx < input.length; ++idx) {
            byte b = input[idx];
            result.append(digits[ (b&0xf0) >> 4 ]);
            result.append(digits[ b&0x0f]);
        }
        return result.toString();
    }

}
