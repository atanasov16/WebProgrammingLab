package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }
    @GetMapping
    public String getAuthorList(Model model){
        model.addAttribute("authors",authorService.listAuthors());
        return "authors";
    }
    @GetMapping("/list")
    public String getBookAuthors(@RequestParam(name = "selectedBookIsbn") String selectedBookIsbn, Model model) {
        if(selectedBookIsbn==null || selectedBookIsbn.isEmpty()){
            return "redirect:/books";
        }
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("bookIsbn", selectedBookIsbn);
        model.addAttribute("book", bookService.findBookByIsbn(selectedBookIsbn));
        return "authorList";
    }

    @PostMapping("/bookdetails/{bookIsbn}")
    public String getBookDetails(@RequestParam (name = "authorId")String authorId,
                                 @RequestParam (name = "bookIsbn")String bookIsbn,
                                 Model model){
        Author author = authorService.findById(Long.parseLong(authorId));
        authorService.save(author);
        bookService.findBookByIsbn(bookIsbn).getBookAuthors().add(author);
        model.addAttribute("authors", bookService.findBookByIsbn(bookIsbn).getBookAuthors());
        model.addAttribute("bookIsbn", bookIsbn);
        model.addAttribute("book", bookService.findBookByIsbn(bookIsbn));
        return "bookDetails";
    }
    @GetMapping("/add")
    public String addAuthor(){
        return "add-author";
    }
    @PostMapping("/add")
    public String addAuthorToList(@RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dateOfBirth){
        Author author = new Author();
        author.setId((long) (Math.random()*1000));
        author.setDateOfBirth(LocalDate.from(dateOfBirth));
        author.setName(name);
        author.setSurname(surname);
        authorService.save(author);
        return "redirect:/author";
    }
}
