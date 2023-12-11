package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }


    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/add")
    public String addBook(Model model){
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        return "add-book";
    }


    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam String year,
                           @RequestParam long bookStoreId){
        BookStore theBookStore = bookStoreService.findAll().stream().filter(b -> b.getId().equals(bookStoreId)).findFirst().get();
        int bookyear = Integer.parseInt(year);
        Book book = new Book(isbn, title, genre, bookyear, theBookStore);
        bookService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        Book book = bookService.listBooks().stream().filter(b -> b.getId()==id).findFirst().get();
        bookService.DeleteById(id);
        return "redirect:/books";
    }
    @PostMapping("/edit/{bookId}")
    public String submitEditBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam String year,
                           @RequestParam Long bookStoreId){
        int bookyear = Integer.parseInt(year);
        Book book = bookService.findBookByIsbn(isbn);
        BookStore bStore = bookStoreService.findAll().stream().filter(b -> b.getId().equals(bookStoreId)).findFirst().get();
        book.setBookStore(bStore);
        book.setId(bookId);
        book.setTitle(title);
        book.setYear(bookyear);
        book.setGenre(genre);
        bookService.DeleteById(bookStoreId);
        bookService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,Model model){
        Book book = this.bookService.listBooks().stream().filter(b -> b.getId()==bookId).findFirst().get();
        model.addAttribute("bookStores", bookStoreService.findAll());
        model.addAttribute("book", book);
        model.addAttribute("bookId",book.getId());
        model.addAttribute("title", book.getTitle());
        model.addAttribute("isbn", book.getIsbn());
        model.addAttribute("genre", book.getGenre());
        model.addAttribute("year", book.getYear());
        model.addAttribute("bookStoreId", book.getBookStoreId());
        return "edit-form";
    }

}
