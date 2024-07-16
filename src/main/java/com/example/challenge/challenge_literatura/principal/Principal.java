package com.example.challenge.challenge_literatura.principal;

import com.example.challenge.challenge_literatura.model.Autor;
import com.example.challenge.challenge_literatura.model.DatosLibro;
import com.example.challenge.challenge_literatura.model.DatosRecibidos;
import com.example.challenge.challenge_literatura.model.Libro;
import com.example.challenge.challenge_literatura.repository.AutorRepository;
import com.example.challenge.challenge_literatura.repository.LibroRepository;
import com.example.challenge.challenge_literatura.service.ConsumoAPI;
import com.example.challenge.challenge_literatura.service.ConvierteDatos;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private AutorRepository autorRepository;
    private LibroRepository libroRepository;

    private List<Autor> autors;
    private List<Libro> librosBD;


    public Principal(AutorRepository repository1, LibroRepository repository2) {
        this.autorRepository = repository1;
        this.libroRepository = repository2;
    }

    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                    Elija la opcion que desea Realizar::
                                    
                    1- Guardar libro 
                    2- Listar libros Registrados
                    3- Listar Autores Registrados
                    4- Listar Autores Vivos en un determinado año
                    5- Listar libros por idiomas
                    6- Buscar libro por Titulo
                    0- Salir
                                    
                    """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroAPI(); //ya
                    break;
                case 2:
                    mostrarLibrosGuardados(); //ya
                    break;
                case 3:
                    mostrarAutoresRegistrados();//ya
                    break;
                case 4:
                    mostrarAutoresVivosXFecha();//ya
                    break;
                case 5:
                    mostrarLibrosXIdioma(); //ya
                    break;
                case 6:
                    buscarLibroPorTituloBD();//ya
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion");
                    break;
                default:
                    System.out.println("Opción inválida");

            }
        }


    }


    private DatosLibro getDatosLibrosAPI() {
        System.out.println("Digite el TITULO del Libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        System.out.println("RECIBIDO en json:::" + json);
        DatosRecibidos librosEncontrados = conversor.obtenerDatos(json, DatosRecibidos.class);
        System.out.println("Convertido:->" + librosEncontrados);
        System.out.println("return=" + librosEncontrados.resultados().get(0));
        return librosEncontrados.resultados().get(0);
    }


    private void buscarLibroAPI() {
        DatosLibro libroB = getDatosLibrosAPI();
        //        Autor autor = new Autor(libro);
//        Autor autor = new Autor(libroB.autor().get(0));
        Libro libro = new Libro(libroB);
        Autor autor = libro.getAutor();
        List<Libro> librosDelAutor = autor.getLibros();

        Optional<Autor> autorOptional = autorRepository.findByNombreIgnoreCase(autor.getNombre());

        if (librosDelAutor == null) {
            librosDelAutor = new ArrayList<>();
        }
        if (autorOptional.isPresent()) {
            try {
                libro.setAutor(autorOptional.get());
                libroRepository.save(libro);
                System.out.printf("libro: %s del autor : %s Guardado en la BD \n", libro.getTitulo(), autor.getNombre());
                return;
            } catch (DataIntegrityViolationException | ConstraintViolationException e) {
                System.out.println("El libro ya existe!");
                System.out.println(libro);
                return;
            }
        }
        librosDelAutor.add(libro);
        autor.setLibros(librosDelAutor);
        System.out.println("LIBROS DEL AUTOR: " + librosDelAutor);
        try {
            autorRepository.save(autor);
        } catch (ConstraintViolationException e) {
            System.out.println("El autor ya esta registrado");
        }

    }


    private void mostrarLibrosGuardados() {
        librosBD = libroRepository.findAll();
        librosBD.forEach(System.out::println);

    }

    private void mostrarAutoresRegistrados() {
        autors = autorRepository.findAll();
        autors.forEach(System.out::println);
    }

    private void mostrarAutoresVivosXFecha() {
        System.out.println("Digite la fecha de la cual quiera conocer los autores vivos");
        var fechaBusqueda = teclado.nextLong();
        List<Autor> autoresVivos = autorRepository.autoresVivos(fechaBusqueda);
        System.out.println(autoresVivos);
    }

    private void mostrarLibrosXIdioma() {
        System.out.println("""
                Digite el idioma del cual quiera buscar libros ::
                en =>  Ingles
                pt => Portugues
                es => Español
                fr => Frances
                it => Italiano
                ja => Japones
                hu => hungaro
                """);
        var idiomaBuscado = teclado.nextLine();
        List<Libro> librosIdiomaEncontrado = libroRepository.librosPorIdioma(idiomaBuscado);
        librosIdiomaEncontrado.forEach(System.out::println);

    }


    private void buscarLibroPorTituloBD() {
        System.out.println("Esribe el nombre del Libro que desea buscar en la BD");
        var nombreLibroBuscado= teclado.nextLine();
        Optional<Libro> libroEncontrado = libroRepository.findByTituloContainsIgnoreCase(nombreLibroBuscado);
        if (libroEncontrado.isPresent()) {
            System.out.println("El libro se encuentra en la BD:");
            System.out.println(libroEncontrado);
        } else {
            System.out.println("El libro no se encuentra en la BD");
        }

    }
}


