package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.AuthorFullname;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJpa;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepository;

    public AuthorServiceImpl(AuthorRepositoryJpa authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findAll().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Optional<Author> save(Long id, String name, String surname) {
        authorRepository.findAll().add(new Author(id,name, surname));
        return Optional.of(authorRepository.save(new Author(id,name, surname)));
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.of(authorRepository.save(author));
    }
}
