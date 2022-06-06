package com.ddutra9.cadastrousuario.infrastructure.repository.impl;

import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.Page;
import com.ddutra9.cadastrousuario.domain.PageRequest;
import com.ddutra9.cadastrousuario.domain.User;
import com.ddutra9.cadastrousuario.domain.repository.UserRepository;
import com.ddutra9.cadastrousuario.infrastructure.model.UserData;
import com.ddutra9.cadastrousuario.infrastructure.repository.UserRepositoryJPA;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserRepositoryImp implements UserRepository {

    private UserRepositoryJPA userRepositoryJPA;
    private Validator validator;

    public UserRepositoryImp(UserRepositoryJPA userRepositoryJPA, Validator validator) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.validator = validator;
    }

    private void validateDataInfra(UserData userData) {
        Set<ConstraintViolation<UserData>> violations = validator.validate(userData);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Override
    public void createUser(User user) {
        UserData userData = new UserData(user.getCpf(), user.getName(), user.getEmail(), user.getPassword());
        validateDataInfra(userData);

        userRepositoryJPA.save(userData);
    }

    @Override
    public UserResponse updateUser(String cpf, User user) {
        UserData userData = userRepositoryJPA.findByCpf(cpf).get();
        userData.setEmail(user.getEmail());
        userData.setName(user.getName());
        userData.setPassword(user.getPassword());

        validateDataInfra(userData);
        userRepositoryJPA.save(userData);

        return userData.toUserResonse();
    }

    @Override
    public Optional<User> findByCPF(String cpf) {
        val userDataOpt = userRepositoryJPA.findByCpf(cpf);
        if(userDataOpt.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(userDataOpt.get().toUser());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> findAllUsers(PageRequest pageRequest) {
        val page = userRepositoryJPA.findAll(org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize()));
        return new Page<>(page.getContent().stream().map(UserData::toUserResonse).toList(), page.getNumber(), page.getSize(), page.getTotalPages());
    }
}
