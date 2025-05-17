package bstore.bookstore.dto.category;

public record CategorySearchParams(
        String[] name,
        String[] description
) {
}
