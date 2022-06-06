package com.ddutra9.cadastrousuario.domain;

public class User {

    private CPF cpf;
    private String name;
    private Email email;
    private Password pwd;

    public User(CPF cpf, String name, Email email, Password pwd) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    public String getCpf() {
        return cpf.getCpf();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getPassword() {
        return pwd.getPassword();
    }
}
