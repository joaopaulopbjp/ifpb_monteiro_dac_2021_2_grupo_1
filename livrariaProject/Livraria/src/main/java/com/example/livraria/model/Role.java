package com.example.livraria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String papel;

    @ManyToMany(mappedBy = "papeis")
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return papel;
    }

    public String getPapel() {
        return papel;
    }
    public void setPapel(String papel) {
        this.papel = papel;
    }

    @Override
    public String toString() {
        return papel;
    }
    
}
