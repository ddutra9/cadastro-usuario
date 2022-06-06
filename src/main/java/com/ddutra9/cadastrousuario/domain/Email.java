package com.ddutra9.cadastrousuario.domain;

import com.ddutra9.cadastrousuario.domain.exception.BusinessException;

public class Email {

    private String email;

    public Email(String email) {
        if(email == null || email.isBlank()) {
            throw new BusinessException("E-mail não pode ser vazio!");
        }

        if (email == null || !email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new BusinessException("E-mail no formato inválido!");
        }

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
