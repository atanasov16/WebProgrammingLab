package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepositoryJpa bookRepository;
    private final AuthorRepositoryJpa authorRepository;

    public BookServiceImpl(BookRepositoryJpa bookRepository, AuthorRepositoryJpa authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        if(bookRepository.findByIsbn(isbn)==null){
            throw new IllegalArgumentException();
        }
        Author author = authorRepository.findAll().stream().filter(a -> a.getId().equals(authorId)).findFirst().orElse(null);
        Book book = bookRepository.findByIsbn(isbn);
        book.getBookAuthors().add(author);
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public double getBookRating(Book book) {
        return book.getBookRating();
    }

    @Override
    public Optional<Book> save(String isbn, String title, String genre, int year, BookStore bookStore) {
        return Optional.of(bookRepository.save(new Book(isbn, title, genre, year, bookStore)));
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void DeleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
