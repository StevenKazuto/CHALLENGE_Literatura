package com.example.challenge.challenge_literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;

    private Long fechaDenacimiento;
    private Long fechaDeMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {

    }

//
//    public Autor(DatosLibro libro) {
//        this.nombre = libro.autor().get(0).nombre();
//        this.fechaDenacimiento = libro.autor().get(0).fechaDenacimiento();
//        this.fechaDeMuerte = libro.autor().get(0).fechaDeMuerte();
//    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDenacimiento = datosAutor.fechaDenacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getFechaDenacimiento() {
        return fechaDenacimiento;
    }

    public void setFechaDenacimiento(Long fechaDenacimiento) {
        this.fechaDenacimiento = fechaDenacimiento;
    }

    public Long getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Long fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l->l.setAutor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "AUTOR{" +
                "nombre='" + nombre + '\'' +
                ", fechaDenacimiento=" + fechaDenacimiento +
                ", fechaDeMuerte=" + fechaDeMuerte;
    }
}

