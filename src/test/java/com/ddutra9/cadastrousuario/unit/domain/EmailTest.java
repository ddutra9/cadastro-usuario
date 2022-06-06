package com.ddutra9.cadastrousuario.unit.domain;

import com.ddutra9.cadastrousuario.domain.CPF;
import com.ddutra9.cadastrousuario.domain.Email;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {

    @Test
    void createEmailInvalidTest() {
        assertThrows(BusinessException.class, () -> new Email(null), "E-mail não pode ser vazio!");
        assertThrows(BusinessException.class, () -> new Email(""), "E-mail não pode ser vazio!");
        assertThrows(BusinessException.class, () -> new Email("ddkjakj@jsksjjs"), "E-mail no formato inválido!");
    }

    @Test
    void createValidEmailTest() {
        String endereco = "pedro@gmail.com";
        Email email = new Email(endereco);
        assertEquals(endereco, email.getEmail());
    }

}
