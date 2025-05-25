package bstore.bookstore.mapper;

import bstore.bookstore.config.MapperConfig;
import bstore.bookstore.dto.book.BookDto;
import bstore.bookstore.dto.book.BookDtoWithoutCategoryIds;
import bstore.bookstore.dto.book.CreateBookRequestDto;
import bstore.bookstore.model.Book;
import bstore.bookstore.model.Category;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "categoriesIds", ignore = true)
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        if (book.getCategories() != null) {
            bookDto.setCategoriesIds(
                    book.getCategories().stream()
                            .map(Category::getId)
                            .collect(Collectors.toSet())
            );
        }
    }

    void updateBook(@MappingTarget Book book, CreateBookRequestDto requestDto);
}
