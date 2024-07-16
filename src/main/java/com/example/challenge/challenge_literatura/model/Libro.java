package com.example.challenge.challenge_literatura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idiomasList;

    private Double numeroDeDescargas;


    public Libro() {

    }
    public Libro(DatosLibro libro) {
        this.titulo = libro.titulo();
        this.autor = new Autor(libro.autor().get(0));
        this.idiomasList = libro.idiomasList().get(0);
        this.numeroDeDescargas = libro.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomasList() {
        return idiomasList;
    }

    public void setIdiomasList(String idiomasList) {
        this.idiomasList = idiomasList;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "TITULO='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomasList='" + idiomasList + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas ;
    }
}
