package bstore.bookstore.dto.category;

public record CreateCategoryRequestDto(
    String name,
    String description
) {
}
