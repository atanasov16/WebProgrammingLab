package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepositoryJpa;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
