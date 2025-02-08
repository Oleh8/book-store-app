package bstore.bookstore.dto;

import java.math.BigDecimal;

public record BookSearchParams(String[] title,
                               String[] author,
                               String[] isbn,
                               BigDecimal minPrice,
                               BigDecimal maxPrice,
                               String[] description) {
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String ISBN = "isbn";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
}
