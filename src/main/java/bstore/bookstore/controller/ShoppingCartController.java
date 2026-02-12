package bstore.bookstore.controller;

import bstore.bookstore.dto.cartitem.CreateCartItemRequestDto;
import bstore.bookstore.dto.cartitem.UpdateCartItemRequestDto;
import bstore.bookstore.dto.shoppingcart.ShoppingCartDto;
import bstore.bookstore.model.User;
import bstore.bookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart management", description = "Endpoints for managing shopping carts")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(summary = "Get user's shopping cart",
            description = "Retrieve the current user's shopping cart with all items")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @PostMapping
    @Operation(summary = "Add book to cart",
            description = "Add a new book or increase quantity of existing book in the cart")
    public ShoppingCartDto addBook(
            Authentication authentication,
            @RequestBody @Valid CreateCartItemRequestDto requestDto
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addBookToCart(user.getId(), requestDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update item quantity",
            description = "Update the quantity of a specific item in the cart")
    public ShoppingCartDto updateItem(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateCartItemRequestDto requestDto
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateCartItem(user.getId(), cartItemId, requestDto);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Remove item from cart",
            description = "Delete a specific item from the shopping cart")
    public void removeItem(Authentication authentication, @PathVariable Long cartItemId) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteCartItem(user.getId(), cartItemId);
    }
}
