package bstore.bookstore.repository.book;

import bstore.bookstore.dto.BookSearchParams;
import bstore.bookstore.model.Book;
import bstore.bookstore.repository.SpecificationBuilder;
import bstore.bookstore.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> specProviderManager;

    @Override
    public Specification<Book> build(BookSearchParams bookSearchParams) {
        Specification<Book> specification = Specification.where(null);
        if (bookSearchParams.title() != null && bookSearchParams.title().length > 0) {
            specification = specification.and(specProviderManager.getProvider("title")
                    .getSpecification(bookSearchParams.title()));
        }
        if (bookSearchParams.author() != null && bookSearchParams.author().length > 0) {
            specification = specification.and(specProviderManager.getProvider("author")
                    .getSpecification(bookSearchParams.author()));
        }
        return specification;
    }
}
