package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepository{
    List<Book> bookList;

    public BookRepository() {
        this.bookList = new ArrayList<>();
        bookList.add(new Book("0060935464", "To Kill a Mockingbird", "Thriller", 1960));
        bookList.add(new Book("0358439191", "The Lord of the Rings", "Fiction", 1954));
        bookList.add(new Book("0060934344", "Don Quixote", "Fiction", 1605));
        bookList.add(new Book("0679723161", "Lolita", "Fiction", 1955));
        bookList.add(new Book("0385319959", "Outlander", "Romance", 1991));
    }

    public List<Book> findAll() {
        return bookList;
    }

    public Book findByIsbn(String isbn) {
        return bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
    Author addAuthorToBook(Author author, Book book){
        book.getBookAuthors().add(author);
        return author;
    }

}