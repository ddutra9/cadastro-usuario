package com.ddutra9.cadastrousuario.domain;

import com.ddutra9.cadastrousuario.domain.exception.BusinessException;

public class CPF {

    private String cpf;

    public CPF(String cpf) {
        if(cpf == null || cpf.isBlank()) {
            throw new BusinessException("CPF não pode ser vazio!");
        }

        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new BusinessException("CPF no formato inválido!");
        }

        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

}
