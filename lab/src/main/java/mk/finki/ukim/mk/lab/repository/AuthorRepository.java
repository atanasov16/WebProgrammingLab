package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AuthorRepository{
    List<Author> authorsRepoList;

    public AuthorRepository() {
        this.authorsRepoList = new ArrayList<>();
        authorsRepoList.add(new Author(1L, "John Ronald Reuel", "Tolkien"));
        authorsRepoList.add(new Author(2L, "Stephen", "King"));
        authorsRepoList.add(new Author(3L, "George", "Orwell"));
        authorsRepoList.add(new Author(4L, "James", "Joyce"));
        authorsRepoList.add(new Author(5L, "J.K.", "Rowling"));

    }

    public List<Author> findAll() {
        return authorsRepoList;
    }
}
