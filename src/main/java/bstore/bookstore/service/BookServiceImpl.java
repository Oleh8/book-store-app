package bstore.bookstore.service;

import bstore.bookstore.dto.BookDto;
import bstore.bookstore.dto.BookSearchParams;
import bstore.bookstore.dto.CreateBookRequestDto;
import bstore.bookstore.exception.EntityNotFoundException;
import bstore.bookstore.mapper.BookMapper;
import bstore.bookstore.model.Book;
import bstore.bookstore.repository.book.BookRepository;
import bstore.bookstore.repository.book.BookSpecBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecBuilder bookSpecBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        bookMapper.updateBook(book, requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public List<BookDto> search(BookSearchParams bookSearchParams) {
        Specification<Book> bookSpecification = bookSpecBuilder.build(bookSearchParams);
        return bookRepository.findAll(bookSpecification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
