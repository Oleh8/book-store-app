package bstore.bookstore.service;

import bstore.bookstore.dto.BookDto;
import bstore.bookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {

    BookDto save(CreateBookRequestDto requestDto);

    BookDto findBookById(Long id);

    List<BookDto> findAll();
}
