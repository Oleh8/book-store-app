package bstore.bookstore.repository.book.specifications;

import static bstore.bookstore.dto.book.BookSearchParams.PRICE;

import bstore.bookstore.model.Book;
import bstore.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecification implements SpecificationProvider<Book> {

    @Override
    public String getKey() {
        return PRICE;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get(PRICE), Double.parseDouble(params[0]),
                        Double.parseDouble(params[1])));
    }
}
