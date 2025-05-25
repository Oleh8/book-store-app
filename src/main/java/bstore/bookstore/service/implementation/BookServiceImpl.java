package bstore.bookstore.service.implementation;

import bstore.bookstore.dto.book.BookDto;
import bstore.bookstore.dto.book.BookDtoWithoutCategoryIds;
import bstore.bookstore.dto.book.BookSearchParams;
import bstore.bookstore.dto.book.CreateBookRequestDto;
import bstore.bookstore.exception.EntityNotFoundException;
import bstore.bookstore.mapper.BookMapper;
import bstore.bookstore.model.Book;
import bstore.bookstore.repository.book.BookRepository;
import bstore.bookstore.repository.book.BookSpecBuilder;
import bstore.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public BookDtoWithoutCategoryIds findBookById(Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Book not found with id: " + id));
        return bookMapper.toDtoWithoutCategories(book);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
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

    public List<BookDto> search(BookSearchParams bookSearchParams, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecBuilder.build(bookSearchParams);
        return bookRepository.findAll(bookSpecification, pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
