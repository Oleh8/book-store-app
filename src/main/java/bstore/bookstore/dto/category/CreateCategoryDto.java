package bstore.bookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotBlank(message = "Category name is required")
    private String name;
    private String description;
}
