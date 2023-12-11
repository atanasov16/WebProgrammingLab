package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookStoreRepository {
    List<BookStore> bookStores;

    public BookStoreRepository() {
        this.bookStores = new ArrayList<>();
        bookStores.add(new BookStore("BookStore1","City1","Address1"));
        bookStores.add(new BookStore("BookStore2","City2","Address2"));
        bookStores.add(new BookStore("BookStore3","City3","Address3"));
        bookStores.add(new BookStore("BookStore4","City4","Address4"));
        bookStores.add(new BookStore("BookStore5","City5","Address5"));
    }
    public List<BookStore> findAll(){
        return bookStores;
    }
}
