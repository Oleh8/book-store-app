package bstore.bookstore.service;

import bstore.bookstore.dto.BookDto;
import bstore.bookstore.dto.BookSearchParams;
import bstore.bookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {

    BookDto save(CreateBookRequestDto requestDto);

    BookDto findBookById(Long id);

    List<BookDto> findAll();

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    public List<BookDto> search(BookSearchParams bookSearchParams);
}
