package com.ddutra9.cadastrousuario.application.request;

import com.ddutra9.cadastrousuario.domain.CPF;
import com.ddutra9.cadastrousuario.domain.Email;
import com.ddutra9.cadastrousuario.domain.Password;
import com.ddutra9.cadastrousuario.domain.User;

public class UpdateUserRequest extends UserRequest {

    public UpdateUserRequest(String name, String email, String password) {
        super(name, email, password);
    }

    public User toDomain(String cpf) {
        return new User(new CPF(cpf), super.getName(), new Email(super.getEmail()), new Password(super.getPassword()));
    }

}
