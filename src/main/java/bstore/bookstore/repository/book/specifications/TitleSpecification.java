package bstore.bookstore.repository.book.specifications;

import bstore.bookstore.model.Book;
import bstore.bookstore.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecification implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "title";
    }

    public Specification<Book> getSpecification(String[] titles) {
        return (root, query, criteriaBuilder) -> root.get("title")
                .in(Arrays.stream(titles).toArray());
    }
}
