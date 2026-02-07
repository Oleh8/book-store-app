package bstore.bookstore.service;

import bstore.bookstore.dto.category.CategoryDto;
import bstore.bookstore.dto.category.CreateCategoryDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryDto createCategoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);

}
