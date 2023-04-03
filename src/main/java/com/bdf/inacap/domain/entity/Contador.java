package com.bdf.inacap.domain.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Contador")
public class Contador implements UserDetails {

    //Empresa del contador logueada en la sesion
    @Transient
    private Empresa empresaLogueada;

    @Enumerated
    @Column(nullable = false,name="tipo")
    private TipoContadorEnum tipo;

    @Column(unique = true, nullable = false, length = 11,name="cuit")
    private String cuit;

    @Column(nullable = false, length = 100,name="razonSocial")
    private String razonSocial;

    @Column(nullable = false, length = 8,name="clave")
    private String clave;

    @Column(nullable = true, length = 20,name="nroTelefono")
    private String nroTelefono;

    @Embedded
    private Domicilio domicilio = new Domicilio();

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaRegistro;

    @Column(name="confirmado")
    private Integer confirmado;//Para notificaciones o pedidos de actualización de datos.

    @Embedded
    private PersonaResponsable responsable;

    @Column(name="mail")
    private String mail;

    @Enumerated
    private ExentoEnum exento = ExentoEnum.NO;

    @Temporal(TemporalType.DATE)
    @Column(name="fechaExento")
    private Calendar fechaExento;


    @Override
    public Object clone() throws CloneNotSupportedException {
        Contador clon = new Contador();
        clon.setId(this.getId());
        clon.setClave(this.getClave());
        clon.setConfirmado(this.getConfirmado());
        clon.setCuit(this.getCuit());
        clon.setDomicilio(this.getDomicilio());
        clon.setFechaRegistro(this.getFechaRegistro());
        clon.setNroTelefono(this.getNroTelefono());
        clon.setRazonSocial(this.getRazonSocial());
        clon.setTipo(this.getTipo());
        clon.setResponsable(this.getResponsable());
        clon.setMail(this.getMail());
        clon.setFechaExento(this.getFechaExento());
        clon.setExento(this.getExento());

        return clon;
    }

//Spring Security Configuration
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        TipoUsuariosEnum rol = TipoUsuariosEnum.CONTADOR;
//        authorities.add(new SimpleGrantedAuthority(rol.name()));
//        if(this.empresaLogueada!=null){
//            TipoUsuariosEnum rolEmpresa = TipoUsuariosEnum.EMPRESA;
//            authorities.add(new SimpleGrantedAuthority(rolEmpresa.name()));
//        }
        return authorities;
    }

}

