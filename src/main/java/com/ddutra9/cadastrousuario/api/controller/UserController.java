package com.ddutra9.cadastrousuario.api.controller;

import com.ddutra9.cadastrousuario.api.ResponseStatusError;
import com.ddutra9.cadastrousuario.application.CrudUserInteractor;
import com.ddutra9.cadastrousuario.application.request.CreateUserRequest;
import com.ddutra9.cadastrousuario.application.request.UpdateUserRequest;
import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.Page;
import com.ddutra9.cadastrousuario.domain.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private CrudUserInteractor crudUserInteractor;

    public UserController(CrudUserInteractor crudUserInteractor) {
        this.crudUserInteractor = crudUserInteractor;
    }

    @PostMapping
    @Operation(summary = "Cria usuário")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ResponseStatusError.class)))})
    public ResponseEntity<String> create(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(this.crudUserInteractor.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Buscar todos usuários")
    public ResponseEntity<Page<UserResponse>> findAll(PageRequest pageRequest) {
        return new ResponseEntity<>(crudUserInteractor.findAll(pageRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/{cpf}")
    @Operation(summary = "Altera usuário por cpf")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ResponseStatusError.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ResponseStatusError.class)))})
    public ResponseEntity<UserResponse> update(@PathVariable("cpf") String cpf, @RequestBody UpdateUserRequest request) {
        return new ResponseEntity<>(crudUserInteractor.update(cpf, request), HttpStatus.OK);
    }
}
