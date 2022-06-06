package com.ddutra9.cadastrousuario.domain.exception;

public class NotFoundException extends RuntimeException {

    private String id;

    public NotFoundException(String message, String id) {
        super(message);
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
