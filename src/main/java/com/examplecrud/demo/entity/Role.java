package com.examplecrud.demo.entity;


import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

    private Long id;
    private String nameRole;

    public Role(Long id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public String getAuthority() {
        return getNameRole();
    }
}
