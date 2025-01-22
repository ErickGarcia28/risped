package com.example.risped.receta.model;

import com.example.risped.paciente.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "receta")
public class Receta {

    //_______________________________________________________________________________
    // Propiedad ID autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //_______________________________________________________________________________
    // Propiedades de tipo VARCHAR (Ajustar la longitud según las preferencias)
    @Column(name = "medicamentos", columnDefinition = "VARCHAR(200)")
    private String medicamentos;

    @Column(name = "sugerencias", columnDefinition = "VARCHAR(200)")
    private String sugerencias;

    @Column(name = "fecha", columnDefinition = "VARCHAR(200)")
    private String fecha;

    @Column(name = "nombre_doctor", columnDefinition = "VARCHAR(200)")
    private String nombreDoctor;

    @Column(name = "paciente", columnDefinition = "VARCHAR(200)")
    private String paciente;



    //_______________________________________________________________________________
    // Constructor vacío
    public Receta() {
        // Constructor sin argumentos para JPA
    }

    //_______________________________________________________________________________
// Constructor completo (incluye paciente)
    public Receta(Long id, String medicamentos, String sugerencias, String fecha, String nombreDoctor) {
        this.id = id;
        this.medicamentos = medicamentos;
        this.sugerencias = sugerencias;
        this.fecha = fecha;
        this.nombreDoctor = nombreDoctor;

    }

    // Constructor sin ID (para nuevas recetas)
    public Receta(String medicamentos, String sugerencias, String fecha, String nombreDoctor) {
        this.medicamentos = medicamentos;
        this.sugerencias = sugerencias;
        this.fecha = fecha;
        this.nombreDoctor = nombreDoctor;
    }

    //_______________________________________________________________________________
    // Getters y Setters para cada propiedad

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}
