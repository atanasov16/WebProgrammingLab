package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookdetails", urlPatterns = "/servlet/bookdetails")
public class BookDetails extends HttpServlet {
    private final TemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookDetails(TemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        String isbn = req.getParameter("bookIsbn");
        Book book = bookService.findBookByIsbn(isbn);
        context.setVariable("book", book);
        springTemplateEngine
                .process("bookDetails.html",context,
                        resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("authorId");
        String isbn = req.getParameter("bookIsbn");
        Long authorId = Long.parseLong(author);
        bookService.addAuthorToBook(authorId, isbn);
        resp.sendRedirect("/bookdetails?bookIsbn=" + isbn);
    }
}
