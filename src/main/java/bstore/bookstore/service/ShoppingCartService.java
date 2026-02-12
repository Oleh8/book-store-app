package bstore.bookstore.service;

import bstore.bookstore.dto.cartitem.CreateCartItemRequestDto;
import bstore.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import bstore.bookstore.dto.shoppingcart.ShoppingCartDto;
import bstore.bookstore.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart(Long userId);

    void createShoppingCartForUser(User user);

    ShoppingCartDto addBookToCart(Long userId, CreateCartItemRequestDto requestDto);

    ShoppingCartDto updateCartItem(Long userId, Long cartItemId,
                                   UpdateCartItemRequestDto requestDto);

    void deleteCartItem(Long userId, Long cartItemId);
}
