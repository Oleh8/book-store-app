package bstore.bookstore.controller;

import bstore.bookstore.dto.BookDto;
import bstore.bookstore.dto.BookSearchParams;
import bstore.bookstore.dto.CreateBookRequestDto;
import bstore.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id")
    public BookDto findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @Operation(summary = "Create new book")
    public BookDto save(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book by id")
    public BookDto updateBookById(@RequestBody @Valid CreateBookRequestDto requestDto,
                                  @PathVariable Long id) {
        return bookService.updateById(id, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search books")
    public List<BookDto> search(BookSearchParams bookSearchParams, Pageable pageable) {
        return bookService.search(bookSearchParams, pageable);
    }
}
