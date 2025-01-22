package com.example.risped.paciente.model;

import com.example.risped.receta.model.Receta;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente {

    //_______________________________________________________________________________
    // Propiedad ID autogenerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //_______________________________________________________________________________
    // Propiedades de tipo VARCHAR (Ajustar la longitud según las preferencias)
    @Column(name = "nombre", columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "VARCHAR(100)")
    private String apellido;

    @Column(name = "alergias", columnDefinition = "VARCHAR(100)")
    private String alergias;

    @Column(name = "peso", columnDefinition = "VARCHAR(100)")
    private String peso;

    @Column(name = "altura", columnDefinition = "VARCHAR(100)")
    private String altura;

    //_______________________________________________________________________________
    // Propiedad de tipo int para almacenar la edad del paciente
    @Column(name = "edad", columnDefinition = "INT")
    private int edad;


    //_______________________________________________________________________________
    // Constructor vacío
    public Paciente() {
        // Constructor sin argumentos para JPA
    }

    public Paciente(Long id, String nombre, String apellido, String alergias, String peso, String altura, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alergias = alergias;
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
    }

    public Paciente(String nombre, String apellido, String alergias, String peso, String altura, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.alergias = alergias;
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
    }

    //_______________________________________________________________________________
    // Getters y Setters para cada propiedad

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



}
