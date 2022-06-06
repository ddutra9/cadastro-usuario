package com.ddutra9.cadastrousuario.integration.infrastructure;

import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.*;
import com.ddutra9.cadastrousuario.domain.repository.UserRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUserWithNoValidLegthCharTest() {
        User user = new User(new CPF("670.409.920-43"),
                "pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira" +
                        "pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira" +
                        "pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira pedro pereira",
                new Email("pedro@gmail.com"),
                new Password("Ola 123"));

        assertThrows(ConstraintViolationException.class, () -> userRepository.createUser(user));
    }

    @Test
    void createUserAndFindByCPFTest() {
        String cpf = "670.409.920-43";
        User user = createUser(cpf);

        Optional<User> userDBOpt = userRepository.findByCPF(cpf);
        assertTrue(userDBOpt.isPresent());

        User userDB = userDBOpt.get();
        assertEquals(user.getCpf(), userDB.getCpf());
        assertEquals(user.getEmail(), userDB.getEmail());
        assertEquals(user.getName(), userDB.getName());
        assertEquals(user.getPassword(), userDB.getPassword());
    }

    @Test
    void createUserAndFindAllTest() {
        String cpf = "365.972.850-00";
        User user = createUser(cpf);

        Page<UserResponse> responses = userRepository.findAllUsers(new PageRequest(0, 10));
        assertEquals(0, responses.totalPages());
        assertEquals(1, responses.getTotal());
        assertEquals(0, responses.getPageNumber());
        assertEquals(10, responses.getPageSize());
        assertTrue(responses.isLast());
        assertNotNull(responses.getContent());
    }

    @Test
    void updateUserAndFindByCPFTest() {
        String cpf = "966.053.200-85";
        createUser(cpf);
        User user = new User(new CPF(cpf),
                "Pedrão pereira",
                new Email("pedro@gmail.com"),
                new Password("Ola 123"));

        userRepository.updateUser(cpf, user);

        Optional<User> userDBOpt = userRepository.findByCPF(cpf);
        assertTrue(userDBOpt.isPresent());

        User userDB = userDBOpt.get();
        assertEquals(user.getCpf(), userDB.getCpf());
        assertEquals(user.getEmail(), userDB.getEmail());
        assertEquals(user.getName(), userDB.getName());
        assertEquals(user.getPassword(), userDB.getPassword());
    }

    private User createUser(String cpf) {
        User user = new User(new CPF(cpf),
                "pedro pereira",
                new Email("pedro@gmail.com"),
                new Password("Ola 123"));

        userRepository.createUser(user);

        return user;
    }
}
