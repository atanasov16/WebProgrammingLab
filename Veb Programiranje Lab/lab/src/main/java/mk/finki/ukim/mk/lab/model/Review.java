package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;
    @ManyToOne
    private Book book;
    private LocalDateTime timestamp;

    public Review(Long id, Integer score, String description, Book book, LocalDateTime timestamp) {
        this.id = id;
        this.score = score;
        this.description = description;
        this.book = book;
        this.timestamp = timestamp;
    }

    public Review() {
    }

}
