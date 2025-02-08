package bstore.bookstore.repository;

import bstore.bookstore.dto.BookSearchParams;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

    Specification<T> build(BookSearchParams bookSearchParams);
}
