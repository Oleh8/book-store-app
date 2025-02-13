package bstore.bookstore.dto;

import bstore.bookstore.validation.Isbn;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String author;
    @NotNull
    @NotBlank
    @Isbn
    private String isbn;
    @Size(min = 10, max = 255)
    private String description;
    private String coverImage;
    @Min(0)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
}
