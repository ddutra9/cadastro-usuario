package com.ddutra9.cadastrousuario.infrastructure;

import com.ddutra9.cadastrousuario.domain.service.PasswordCipher;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordCipherMD5 implements PasswordCipher {
    @Override
    public String passwordCipher(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("erro ao gerar hash da senha");
        }
    }

    @Override
    public boolean validateEncryptedPassword(String encyptedPwd, String password) {
        return encyptedPwd.equals(passwordCipher(password));
    }
}
