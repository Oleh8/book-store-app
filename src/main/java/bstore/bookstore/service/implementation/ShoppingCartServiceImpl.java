package bstore.bookstore.service.implementation;

import bstore.bookstore.dto.cartitem.CreateCartItemRequestDto;
import bstore.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import bstore.bookstore.dto.shoppingcart.ShoppingCartDto;
import bstore.bookstore.exception.EntityNotFoundException;
import bstore.bookstore.mapper.CartItemMapper;
import bstore.bookstore.mapper.ShoppingCartMapper;
import bstore.bookstore.model.Book;
import bstore.bookstore.model.CartItem;
import bstore.bookstore.model.ShoppingCart;
import bstore.bookstore.model.User;
import bstore.bookstore.repository.book.BookRepository;
import bstore.bookstore.repository.caritem.CartItemRepository;
import bstore.bookstore.repository.shoppingcart.ShoppingCartRepository;
import bstore.bookstore.service.ShoppingCartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional(readOnly = true)
    public ShoppingCartDto getShoppingCart(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .map(shoppingCartMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find shopping cart by userId: " + userId));
    }

    @Override
    @Transactional
    public ShoppingCartDto addBookToCart(Long userId, CreateCartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);

        Book book = bookRepository.findById(requestDto.bookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id: " + requestDto.bookId()));

        Optional<CartItem> existingItem =
                shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(requestDto.bookId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + requestDto.quantity());
        } else {
            CartItem cartItem = cartItemMapper.toModel(requestDto);
            cartItem.setShoppingCart(shoppingCart);
            cartItem.setBook(book);
            shoppingCart.getCartItems().add(cartItem);
        }

        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    @Transactional
    public ShoppingCartDto updateCartItem(Long userId, Long cartItemId,
                                          UpdateCartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        CartItem cartItem = getCartItemByCartItemIdShoppingCartId(cartItemId, shoppingCart.getId());
        cartItem.setQuantity(requestDto.quantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long userId, Long cartItemId) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        CartItem cartItem = getCartItemByCartItemIdShoppingCartId(cartItemId, shoppingCart.getId());
        shoppingCart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
    }

    @Override
    @Transactional
    public void createShoppingCartForUser(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find shopping cart by userId: " + userId));
    }

    private CartItem getCartItemByCartItemIdShoppingCartId(Long cartItemId, Long shoppingCartId) {
        return cartItemRepository
                .findByIdAndShoppingCartId(cartItemId, shoppingCartId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cart item by id: " + cartItemId
                                + " for shoppingCartId: " + shoppingCartId));
    }
}
