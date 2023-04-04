package com.bdf.inacap.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@AllArgsConstructor
@Getter
@Setter
@Entity
public class Empresa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String fechaInicioActividadString;

    @Transient
    private String fechaRegistroString;

    @Transient
    private String fechaExentoString;

    @Column(unique = true, nullable = false, length = 11, name = "cuit")
    private String cuit;

    @Column(nullable = false, length = 100, name = "razonSocial")
    private String razonSocial;

    @Column(nullable = false, length = 70, name = "mail")
    private String mail;

    @Column(length = 70, name = "mailAlternativo")
    private String mailAlternativo;

    @Column(nullable = false, length = 8, name = "clave")
    private String clave;

    @Column(nullable = false, length = 5, name = "cantidadEmpleados")
    private Long cantidadEmpleados;

    @Column(length = 100, name = "personaContacto")
    private String personaContacto;

    @Column(nullable = true, length = 20, name = "nroTelefono")
    private String nroTelefono;

    @Embedded
    private Domicilio domicilio;

    @Column(name = "loginsInvalidos")
    private Integer loginsInvalidos;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaRegistro")
    private Calendar fechaRegistro;

    @Column(name = "acuerdo5000")
    private Integer acuerdo5000;

    @Enumerated
    private ExentoEnum exento;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaExento")
    private Calendar fechaExento;

    @Column(name = "fechaInicioActividad")
    private Date fechaInicioActividad;

    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    @Column(name = "confirmado")
    private Integer confirmado;

    @ManyToOne
    @JoinColumn(name = "mayorPeriodoEmitido_id")
    private Periodo mayorPeriodoEmitido;

    @ManyToOne
    @JoinColumn(name = "mayorPeriodoPagado_id")
    private Periodo mayorPeriodoPagado;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("fechaAlta DESC")
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private List<Comentarios> comentariosList = new ArrayList<>();

    public Empresa() {
        super();
        this.domicilio = new Domicilio();
        this.loginsInvalidos = 0;
        this.acuerdo5000 = 0;
        this.exento = ExentoEnum.NO;
    }

    public Date getRealFechaInicioAct() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(fechaInicioActividadString);
    }


    //Spring Security Configuration
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        TipoUsuariosEnum rol = TipoUsuariosEnum.EMPRESA;
//        authorities.add(new SimpleGrantedAuthority(rol.name()));
        return authorities;
    }

    public String getUsername() {
        return cuit;
    }

    public String getPassword() {
        return clave;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
