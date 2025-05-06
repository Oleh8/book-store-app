package bstore.bookstore.mapper;

import bstore.bookstore.config.MapperConfig;
import bstore.bookstore.dto.book.BookDto;
import bstore.bookstore.dto.book.CreateBookRequestDto;
import bstore.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    void updateBook(@MappingTarget Book book, CreateBookRequestDto requestDto);
}
