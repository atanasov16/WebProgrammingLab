package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Book {
    String isbn;
    String title;
    String genre;
    int year;
    List<Author> bookAuthors;

    public Book(String isbn, String title, String genre, int year) {
        this.bookAuthors = new ArrayList<>();
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
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

}
