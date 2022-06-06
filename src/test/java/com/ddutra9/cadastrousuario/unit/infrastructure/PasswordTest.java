package com.ddutra9.cadastrousuario.unit.infrastructure;

import com.ddutra9.cadastrousuario.application.impl.UserCRUDImpl;
import com.ddutra9.cadastrousuario.application.request.CreateUserRequest;
import com.ddutra9.cadastrousuario.domain.User;
import com.ddutra9.cadastrousuario.domain.repository.UserRepository;
import com.ddutra9.cadastrousuario.domain.service.PasswordCipher;
import com.ddutra9.cadastrousuario.infrastructure.PasswordCipherMD5;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PasswordTest {

    private UserCRUDImpl userCRUD;
    private UserRepository userRepository;
    private PasswordCipher passwordCipher;

    public PasswordTest() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.passwordCipher = new PasswordCipherMD5();
        this.userCRUD = new UserCRUDImpl(this.userRepository, this.passwordCipher);
    }

    @Test
    void createUserValidAndVerifyPwdTest() {
        String password = "Ola 123!";
        CreateUserRequest createUserRequest = new CreateUserRequest("pedro pereira", "pedro@gmail.com", password, "670.409.920-43");
        val cpf = userCRUD.create(createUserRequest);
        assertEquals(createUserRequest.getCpf(), cpf);

        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        verify(userRepository).createUser(captorUser.capture());

        User user = captorUser.getValue();
        assertNotEquals(password, user.getPassword());
    }

}
