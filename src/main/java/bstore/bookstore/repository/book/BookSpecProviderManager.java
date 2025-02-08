package bstore.bookstore.repository.book;

import bstore.bookstore.model.Book;
import bstore.bookstore.repository.SpecificationProvider;
import bstore.bookstore.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecProviders;

    @Override
    public SpecificationProvider<Book> getProvider(String key) {
        return bookSpecProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find spec provider for key: " + key));
    }
}
