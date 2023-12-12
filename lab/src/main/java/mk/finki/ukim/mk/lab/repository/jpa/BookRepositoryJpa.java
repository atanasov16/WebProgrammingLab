package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.AuthorFullname;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
    List<Book> findBookByTitleLike(String title);
    List<Book> findBookByGenreContainingIgnoreCase(String genre);
}
