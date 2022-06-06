package com.ddutra9.cadastrousuario.domain;

import com.ddutra9.cadastrousuario.domain.exception.BusinessException;

public class Password {

    private String password;

    public Password(String password) {
        if (password == null || password.isBlank()) {
            throw new BusinessException("Senha não pode ser vazia!");
        }

        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
