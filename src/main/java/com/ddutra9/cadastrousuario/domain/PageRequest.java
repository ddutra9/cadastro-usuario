package com.ddutra9.cadastrousuario.domain;

public class PageRequest {

    private Integer page;
    private Integer size;

    public PageRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Integer offset() {
        return page * size;
    }

}
