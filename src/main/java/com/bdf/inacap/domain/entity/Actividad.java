package com.bdf.inacap.domain.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Actividad")
public class Actividad{

    @Column(unique = true, nullable = false,name="actividad")
    private String actividad;


}
