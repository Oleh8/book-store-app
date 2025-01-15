package bstore.bookstore.service;

import bstore.bookstore.model.Book;
import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();
}
