package bstore.bookstore.mapper;

import bstore.bookstore.config.MapperConfig;
import bstore.bookstore.dto.category.CategoryDto;
import bstore.bookstore.dto.category.CreateCategoryDto;
import bstore.bookstore.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryDto createCategoryDto);
}
