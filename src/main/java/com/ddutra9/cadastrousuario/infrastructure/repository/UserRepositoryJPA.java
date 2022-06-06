package com.ddutra9.cadastrousuario.infrastructure.repository;

import com.ddutra9.cadastrousuario.infrastructure.model.UserData;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepositoryJPA extends PagingAndSortingRepository<UserData, Long> {

    Optional<UserData> findByCpf(String cpf);

}
