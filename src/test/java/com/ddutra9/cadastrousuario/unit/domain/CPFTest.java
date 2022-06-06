package com.ddutra9.cadastrousuario.unit.domain;

import com.ddutra9.cadastrousuario.domain.CPF;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CPFTest {

    @Test
    void createCpfInvalidTest() {
        assertThrows(BusinessException.class, () -> new CPF(null), "CPF não pode ser vazio!");
        assertThrows(BusinessException.class, () -> new CPF(""), "CPF não pode ser vazio!");
        assertThrows(BusinessException.class, () -> new CPF("12345678900"), "CPF no formato inválido!");
    }

    @Test
    void createValidCpfTest() {
        String numero = "123.456.789-00";
        CPF cpf = new CPF(numero);
        assertEquals(numero, cpf.getCpf());
    }

}
