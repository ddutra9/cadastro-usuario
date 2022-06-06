package com.ddutra9.cadastrousuario.unit.api;

import com.ddutra9.cadastrousuario.api.ResponseStatusError;
import com.ddutra9.cadastrousuario.api.UserApiExceptionHandler;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import com.ddutra9.cadastrousuario.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserHandlerTest {

    UserApiExceptionHandler userApiExceptionHandler = new UserApiExceptionHandler();

    @Test
    void validateBodyNotFoundResonseExceptionTest() {
        NotFoundException notFoundException = new NotFoundException("Usuario não encontrado!", "123");
        ResponseEntity error = userApiExceptionHandler.exceptions(notFoundException);
        assertEquals(ResponseStatusError.class, error.getBody().getClass());

        ResponseStatusError responseStatusError =  (ResponseStatusError)error.getBody();
        assertEquals("Usuario não encontrado!", responseStatusError.getMessage());
        assertEquals("Usuario não encontrado!", notFoundException.getMessage());
        assertEquals("123", notFoundException.getId());
    }

    @Test
    void validateBodyResonseExceptionTest() {
        ResponseEntity error = userApiExceptionHandler.exceptions(new BusinessException("CPF invalido!"));
        assertEquals(ResponseStatusError.class, error.getBody().getClass());

        ResponseStatusError responseStatusError =  (ResponseStatusError)error.getBody();
        assertEquals("CPF invalido!", responseStatusError.getMessage());
        assertEquals(400, responseStatusError.getStatus());
    }

}
