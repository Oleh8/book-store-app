package bstore.bookstore.repository.book.specifications;

import static bstore.bookstore.dto.book.BookSearchParams.ISBN;

import bstore.bookstore.model.Book;
import bstore.bookstore.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecification implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return ISBN;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(ISBN)
                .in(Arrays.stream(params).toArray());
    }
}
