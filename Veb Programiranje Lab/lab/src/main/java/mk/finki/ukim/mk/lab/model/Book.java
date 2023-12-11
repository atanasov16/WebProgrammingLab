package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String isbn;
    String title;
    String genre;
    int year;
    @ManyToMany
    List<Author> bookAuthors;
    private long id;
    @ManyToOne
    private BookStore bookStore;
    public Book(){}

    public Book(String isbn, String title, String genre, int year, BookStore bookStore) {
        this.bookAuthors = new ArrayList<>();
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.id=(long) (Math.random()*1000);
        this.bookStore = bookStore;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getBookAuthors() {
        return bookAuthors;
    }
    public String getBookStore(){
        return bookStore.getName();
    }
    public Long getBookStoreId(){
        return this.bookStore.getId();
    }
}
