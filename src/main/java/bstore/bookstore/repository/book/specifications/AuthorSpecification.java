package bstore.bookstore.repository.book.specifications;

import static bstore.bookstore.dto.BookSearchParams.AUTHOR;

import bstore.bookstore.model.Book;
import bstore.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecification implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return AUTHOR;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(AUTHOR), "%" + params[0] + "%");
    }
}
