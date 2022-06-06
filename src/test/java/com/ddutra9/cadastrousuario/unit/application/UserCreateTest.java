package com.ddutra9.cadastrousuario.unit.application;

import com.ddutra9.cadastrousuario.application.impl.UserCRUDImpl;
import com.ddutra9.cadastrousuario.application.request.CreateUserRequest;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import com.ddutra9.cadastrousuario.domain.repository.UserRepository;
import com.ddutra9.cadastrousuario.domain.service.PasswordCipher;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserCreateTest {

    private UserCRUDImpl userCRUD;
    private UserRepository userRepository;
    private PasswordCipher passwordCipher;

    public UserCreateTest() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.passwordCipher = Mockito.mock(PasswordCipher.class);
        this.userCRUD = new UserCRUDImpl(this.userRepository, this.passwordCipher);
    }

    @Test
    void createUserWithNoUpperCharTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest("pedro pereira", "pedro@gmail.com", "123", "670.409.920-43");
        assertThrows(BusinessException.class, () -> userCRUD.create(createUserRequest), "Senha deve conter um caractere maiusculo!");
    }

    @Test
    void createUserWithNoNumberTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest("pedro pereira", "pedro@gmail.com", "Ola", "670.409.920-43");
        assertThrows(BusinessException.class, () -> userCRUD.create(createUserRequest), "Senha deve conter número!");
    }

    @Test
    void createUserAlreadyCreatedTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest("pedro pereira", "pedro@gmail.com", "Ola 123!", "670.409.920-43");
        Mockito.when(userRepository.findByCPF(createUserRequest.getCpf())).thenReturn(Optional.of(createUserRequest.toDomain()));

        assertThrows(BusinessException.class, () -> userCRUD.create(createUserRequest), "Usuário já cadastrado!");
    }

    @Test
    void createUserValidTest() {
        CreateUserRequest createUserRequest = new CreateUserRequest("pedro pereira", "pedro@gmail.com", "Ola 123!", "670.409.920-43");
        Mockito.when(passwordCipher.passwordCipher(createUserRequest.getPassword())).thenReturn(createUserRequest.getPassword());
        val cpf = userCRUD.create(createUserRequest);
        assertEquals(createUserRequest.getCpf(), cpf);
    }

}
