package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class BookRepository{
    List<Book> bookList;
    private BookStoreRepository bookStoreRepository;
    public BookRepository(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
        this.bookList = new ArrayList<>();
        Random random = new Random();
        int size = bookStoreRepository.findAll().size();
        BookStore bookStore = bookStoreRepository.findAll().get(random.nextInt(size));
        bookList.add(new Book("0060935464", "To Kill a Mockingbird", "Thriller", 1960, bookStore));
        bookStore = bookStoreRepository.bookStores.get(random.nextInt(size));
        bookList.add(new Book("0358439191", "The Lord of the Rings", "Fiction", 1954,bookStore));
        bookStore = bookStoreRepository.bookStores.get(random.nextInt(size));
        bookList.add(new Book("0060934344", "Don Quixote", "Fiction", 1605,bookStore));
        bookStore = bookStoreRepository.bookStores.get(random.nextInt(size));
        bookList.add(new Book("0679723161", "Lolita", "Fiction", 1955,bookStore));
        bookStore = bookStoreRepository.bookStores.get(random.nextInt(size));
        bookList.add(new Book("0385319959", "Outlander", "Romance", 1991,bookStore));
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