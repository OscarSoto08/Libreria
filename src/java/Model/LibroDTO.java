/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.text.Normalizer;

/**
 *
 * @author oscar
 */
public class LibroDTO implements Serializable {

    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;
    private String slug;
    private int id;

    public LibroDTO() {
    }

    public LibroDTO(Integer id) {
        this.id = id;
    }

    public LibroDTO(int id, String isbn, String titulo, String autor, String editorial, int anio, String slug) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.slug = slug;
    }
    
    public LibroDTO(String isbn, String titulo, String autor, String editorial, int anio, String slug) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.slug = slug;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Model.Libro[ id=" + id + " ]";
    }
    
    public static String toSlug(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        
        // 1. Convertir a minúsculas
        String slug = text.toLowerCase();

        // 2. Eliminar acentos y normalizar texto
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 3. Reemplazar caracteres no deseados (.,?! etc.) por espacios
        slug = slug.replaceAll("[^a-z0-9\\s-]", " ");

        // 4. Reemplazar espacios múltiples por un único guion
        slug = slug.trim().replaceAll("\\s+", "-");

        // 5. Eliminar guiones al inicio y al final
        slug = slug.replaceAll("^-|-$", "");

        return slug;
    }
    
}
