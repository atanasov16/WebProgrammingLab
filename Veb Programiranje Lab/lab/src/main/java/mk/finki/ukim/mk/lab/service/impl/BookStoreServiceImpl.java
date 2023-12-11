package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepositoryJpa bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepositoryJpa bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }
}
