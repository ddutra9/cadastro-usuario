package com.ddutra9.cadastrousuario.application.request;

import com.ddutra9.cadastrousuario.domain.CPF;
import com.ddutra9.cadastrousuario.domain.Email;
import com.ddutra9.cadastrousuario.domain.Password;
import com.ddutra9.cadastrousuario.domain.User;

public class CreateUserRequest extends UserRequest {

    private String cpf;

    public CreateUserRequest(String name, String email, String password, String cpf) {
        super(name, email, password);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public User toDomain() {
        return new User(new CPF(this.cpf), super.getName(), new Email(super.getEmail()), new Password(super.getPassword()));
    }

}
