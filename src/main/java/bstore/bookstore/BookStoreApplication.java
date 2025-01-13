package bstore.bookstore;

import bstore.bookstore.model.Book;
import bstore.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("My book");
            book.setAuthor("Me");
            book.setIsbn("1234567890");
            book.setPrice(BigDecimal.valueOf(50));
            book.setDescription("Boring");
            book.setCoverImage("Hard cover");

            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
