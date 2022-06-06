package com.ddutra9.cadastrousuario.domain.repository;

import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.Page;
import com.ddutra9.cadastrousuario.domain.PageRequest;
import com.ddutra9.cadastrousuario.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void createUser(User user);

    UserResponse updateUser(String cpf, User user);

     Optional<User> findByCPF(String cpf);

    Page<UserResponse> findAllUsers(PageRequest pageRequest);

}
