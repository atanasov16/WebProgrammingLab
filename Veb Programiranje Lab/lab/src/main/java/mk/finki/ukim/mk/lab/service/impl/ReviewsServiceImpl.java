package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepositoryJpa;
import mk.finki.ukim.mk.lab.service.ReviewsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ReviewsServiceImpl implements ReviewsService {
    private final ReviewRepositoryJpa reviewRepositoryJpa;

    public ReviewsServiceImpl(ReviewRepositoryJpa reviewRepositoryJpa) {
        this.reviewRepositoryJpa = reviewRepositoryJpa;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepositoryJpa.findAll();
    }

    @Override
    public Optional<Review> save(Integer score, String description, Book book, LocalDateTime timestamp) {
        return Optional.of(reviewRepositoryJpa.save(new Review(score, description, book, timestamp)));
    }
}
