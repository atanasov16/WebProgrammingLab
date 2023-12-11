package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;
    private final BookService bookService;

    public ReviewsController(ReviewsService reviewsService, BookService bookService) {
        this.reviewsService = reviewsService;
        this.bookService = bookService;
    }


    @GetMapping()
    public String bookStoreList(Model model){
        model.addAttribute("reviews", reviewsService.findAll());
        return "reviews";
    }

    @GetMapping("/add")
    public String addReview(Model model){
        return "add-review";
    }
    @PostMapping("/add")
    public String saveReview(@RequestParam int score,
                           @RequestParam String description,
                             @RequestParam String bookIsbn,
                             @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime timestamp){
        Book book = bookService.findBookByIsbn(bookIsbn);
        reviewsService.save(score, description,  book, timestamp);
        return "redirect:/reviews";
    }
}
