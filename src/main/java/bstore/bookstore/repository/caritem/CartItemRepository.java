package bstore.bookstore.repository.caritem;

import bstore.bookstore.model.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Допомагає знайти товар і одночасно перевірити, чи належить він конкретному кошику
    Optional<CartItem> findByIdAndShoppingCartId(Long id, Long shoppingCartId);
}
