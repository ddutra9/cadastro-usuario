package com.ddutra9.cadastrousuario.domain.service;

public interface PasswordCipher {

    String passwordCipher(String password);

    boolean validateEncryptedPassword(String encyptedPwd, String password);

}
