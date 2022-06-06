package com.ddutra9.cadastrousuario.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseStatusError {
    private Integer status;
    private String message;
}

