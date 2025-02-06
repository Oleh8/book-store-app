package bstore.bookstore.dto;

public record BookSearchParams(String[] title, String[] author, String[] isbn) {
}
