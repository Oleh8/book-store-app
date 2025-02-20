package bstore.bookstore.service;

import bstore.bookstore.dto.BookDto;
import bstore.bookstore.dto.BookSearchParams;
import bstore.bookstore.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(CreateBookRequestDto requestDto);

    BookDto findBookById(Long id);

    List<BookDto> findAll(Pageable pageable);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParams bookSearchParams, Pageable pageable);
}
