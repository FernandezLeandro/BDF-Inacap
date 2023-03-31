package com.bdf.inacap.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails;

@SuppressWarnings("serial")
@Entity
@Table(name="Usuario")
@Data
public class Usuario extends Auditable implements UserDetails{

    @Column( unique = true, nullable = false, name="login")
    private String login;

    @Column(nullable = false, name="password")
    private String password;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="borrado")
    boolean borrado = false;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Rol role;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="roleMirror_id")
    private Rol roleMirror;

    @Column(name="aprobadorCantidadPagos")
    private boolean aprobadorCantidadPagos;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambioContrasena;

    @Transient
    private boolean alreadyEncripted=false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        TipoUsuariosEnum rol = TipoUsuariosEnum.USUARIO;
        authorities.add(new SimpleGrantedAuthority(rol.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public boolean isAprobadorCantidadPagos() {
        return aprobadorCantidadPagos;
    }

    public boolean getAprobadorCantidadPagos() {
        return aprobadorCantidadPagos;
    }

    public String getNombreCompleto(){
        return this.getNombre() + " " + this.getApellido();
    }

}
