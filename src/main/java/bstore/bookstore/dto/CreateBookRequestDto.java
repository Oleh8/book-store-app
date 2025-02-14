package bstore.bookstore.dto;

import bstore.bookstore.validation.Isbn;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;
    @NotBlank
    @Size(min = 1, max = 255)
    private String author;
    @NotBlank
    @Isbn
    private String isbn;
    @Size(min = 10, max = 255)
    private String description;
    private String coverImage;
    @Positive
    @Digits(integer = 7, fraction = 2)
    private BigDecimal price;
}
