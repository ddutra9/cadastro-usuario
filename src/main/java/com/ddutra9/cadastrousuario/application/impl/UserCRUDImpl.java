package com.ddutra9.cadastrousuario.application.impl;

import com.ddutra9.cadastrousuario.application.CrudUserInteractor;
import com.ddutra9.cadastrousuario.application.request.CreateUserRequest;
import com.ddutra9.cadastrousuario.application.request.UpdateUserRequest;
import com.ddutra9.cadastrousuario.application.request.UserRequest;
import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.Page;
import com.ddutra9.cadastrousuario.domain.PageRequest;
import com.ddutra9.cadastrousuario.domain.User;
import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import com.ddutra9.cadastrousuario.domain.exception.NotFoundException;
import com.ddutra9.cadastrousuario.domain.repository.UserRepository;
import com.ddutra9.cadastrousuario.domain.service.PasswordCipher;
import com.ddutra9.cadastrousuario.application.utils.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class UserCRUDImpl implements CrudUserInteractor {

    private UserRepository userRepository;
    private PasswordCipher passwordCipher;

    @Inject
    public UserCRUDImpl(UserRepository userRepository, PasswordCipher passwordCipher) {
        this.userRepository = userRepository;
        this.passwordCipher = passwordCipher;
    }

    private void validatePassword(String password) {
        if (!StringUtils.containsUpperCaseLetter(password)) {
            throw new BusinessException("Senha deve conter um caractere maiusculo!");
        }

        if (!StringUtils.containsNumber(password)) {
            throw new BusinessException("Senha deve conter número!");
        }
    }

    @Override
    public String create(CreateUserRequest request) {
        validatePassword(request.getPassword());
        setPasswordCipher(request);

        Optional<User> userOptional = userRepository.findByCPF(request.getCpf());
        if(userOptional.isPresent()) {
            throw new BusinessException("Usuário já cadastrado!");
        }

        userRepository.createUser(request.toDomain());
        return request.getCpf();
    }

    @Override
    public UserResponse update(String cpf, UpdateUserRequest request) {
        User user = userRepository.findByCPF(cpf).orElseThrow(() -> new NotFoundException("Usuário não encontrado", cpf));
        if(!passwordCipher.validateEncryptedPassword(user.getPassword(), request.getPassword())) {
            throw new BusinessException("Senha não pode ser diferente da cadastrada!");
        }


        return userRepository.updateUser(cpf, request.toDomain(cpf));
    }

    @Override
    public Page<UserResponse> findAll(PageRequest pageRequest) {
        return userRepository.findAllUsers(pageRequest);
    }

    private void setPasswordCipher(UserRequest request) {
        String pwd = passwordCipher.passwordCipher(request.getPassword());
        request.setPassword(pwd);
    }

}
