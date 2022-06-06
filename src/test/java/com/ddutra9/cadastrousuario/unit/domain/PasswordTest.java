package com.ddutra9.cadastrousuario.unit.domain;

import com.ddutra9.cadastrousuario.domain.Email;
import com.ddutra9.cadastrousuario.domain.Password;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {

    @Test
    void creatPasswordInvalidTest() {
        assertThrows(BusinessException.class, () -> new Password(null), "Senha não pode ser vazia!");
        assertThrows(BusinessException.class, () -> new Password(""), "Senha não pode ser vazia!");
    }

    @Test
    void createValidPasswordTest() {
        String pwd = "Teste 123";
        Password password = new Password(pwd);
        assertEquals(pwd, password.getPassword());
    }

}
