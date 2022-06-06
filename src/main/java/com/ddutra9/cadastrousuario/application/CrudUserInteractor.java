package com.ddutra9.cadastrousuario.application;

import com.ddutra9.cadastrousuario.application.request.CreateUserRequest;
import com.ddutra9.cadastrousuario.application.request.UpdateUserRequest;
import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.Page;
import com.ddutra9.cadastrousuario.domain.PageRequest;

public interface CrudUserInteractor {

    String create(CreateUserRequest request);

    UserResponse update(String cpf, UpdateUserRequest request);

    Page<UserResponse> findAll(PageRequest pageRequest);

}
