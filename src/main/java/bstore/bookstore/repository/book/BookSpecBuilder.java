package bstore.bookstore.repository.book;

import static bstore.bookstore.dto.BookSearchParams.AUTHOR;
import static bstore.bookstore.dto.BookSearchParams.DESCRIPTION;
import static bstore.bookstore.dto.BookSearchParams.ISBN;
import static bstore.bookstore.dto.BookSearchParams.PRICE;
import static bstore.bookstore.dto.BookSearchParams.TITLE;

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
            specification = specification.and(specProviderManager.getProvider(TITLE)
                    .getSpecification(bookSearchParams.title()));
        }
        if (bookSearchParams.author() != null && bookSearchParams.author().length > 0) {
            specification = specification.and(specProviderManager.getProvider(AUTHOR)
                    .getSpecification(bookSearchParams.author()));
        }
        if (bookSearchParams.isbn() != null && bookSearchParams.isbn().length > 0) {
            specification = specification.and(specProviderManager.getProvider(ISBN)
                    .getSpecification(bookSearchParams.isbn()));
        }
        if (bookSearchParams.minPrice() != null && bookSearchParams.maxPrice() != null) {
            specification = specification.and(specProviderManager.getProvider(PRICE)
                    .getSpecification(new String[]{bookSearchParams.minPrice().toString(),
                            bookSearchParams.maxPrice().toString()}));
        }
        if (bookSearchParams.description() != null && bookSearchParams.description().length > 0) {
            specification = specification.and(specProviderManager.getProvider(DESCRIPTION)
                    .getSpecification(bookSearchParams.description()));
        }
        return specification;
    }
}
