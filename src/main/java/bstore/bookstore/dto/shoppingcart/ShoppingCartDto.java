package bstore.bookstore.dto.shoppingcart;

import bstore.bookstore.dto.cartitem.CartItemDto;
import java.util.Set;

public record ShoppingCartDto(
        Long id,
        Long userId,
        Set<CartItemDto> cartItems
) {}

