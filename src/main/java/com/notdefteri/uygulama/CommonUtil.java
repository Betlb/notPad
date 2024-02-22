package com.notdefteri.uygulama;

import lombok.Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Data
public class CommonUtil {
    public static String generateSHA256(String key) throws NoSuchAlgorithmException {
        key = key+"9fj43rf^%+&/";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(key.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
