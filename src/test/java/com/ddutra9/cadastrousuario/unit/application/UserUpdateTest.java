package com.ddutra9.cadastrousuario.unit.application;

import com.ddutra9.cadastrousuario.application.impl.UserCRUDImpl;
import com.ddutra9.cadastrousuario.application.request.CreateUserRequest;
import com.ddutra9.cadastrousuario.application.request.UpdateUserRequest;
import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.User;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import com.ddutra9.cadastrousuario.domain.exception.NotFoundException;
import com.ddutra9.cadastrousuario.domain.repository.UserRepository;
import com.ddutra9.cadastrousuario.domain.service.PasswordCipher;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserUpdateTest {

    private UserCRUDImpl userCRUD;
    private UserRepository userRepository;
    private PasswordCipher passwordCipher;

    public UserUpdateTest() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.passwordCipher = Mockito.mock(PasswordCipher.class);
        this.userCRUD = new UserCRUDImpl(this.userRepository, this.passwordCipher);
    }

    @Test
    void updateUserNotFoundTest() {
        String cpf = "670.409.920-43";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("pedro pereira", "pedro@gmail.com", "Ola 123");

        assertThrows(NotFoundException.class, () -> userCRUD.update(cpf, updateUserRequest), "Usuário não encontrado");
    }

    @Test
    void updateUserWithAnotherPwdTest() {
        String cpf = "670.409.920-43";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("pedro pereira", "pedro@gmail.com", "Ola 123");
        Mockito.when(userRepository.findByCPF(cpf)).thenReturn(Optional.of(updateUserRequest.toDomain(cpf)));
        Mockito.when(passwordCipher.validateEncryptedPassword(updateUserRequest.getPassword(), updateUserRequest.getPassword())).thenReturn(false);

        assertThrows(BusinessException.class, () -> userCRUD.update(cpf, updateUserRequest), "Senha não pode ser diferente da cadastrada!");
    }

    @Test
    void updateUserValidTest() {
        String cpf = "670.409.920-43";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("pedro pereira", "pedro@gmail.com", "Ola 123");
        Mockito.when(userRepository.findByCPF(cpf)).thenReturn(Optional.of(updateUserRequest.toDomain(cpf)));
        Mockito.when(passwordCipher.passwordCipher(updateUserRequest.getPassword())).thenReturn(updateUserRequest.getPassword());
        Mockito.when(passwordCipher.validateEncryptedPassword(updateUserRequest.getPassword(), updateUserRequest.getPassword())).thenReturn(true);

        userCRUD.update(cpf, updateUserRequest);

        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        verify(userRepository).updateUser(ArgumentMatchers.anyString(), captorUser.capture());

        User user = captorUser.getValue();
        assertEquals(cpf, user.getCpf());
        assertEquals(updateUserRequest.getEmail(), user.getEmail());
        assertEquals(updateUserRequest.getPassword(), user.getPassword());
        assertEquals(updateUserRequest.getName(), user.getName());
    }

}
