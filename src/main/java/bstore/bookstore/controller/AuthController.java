package bstore.bookstore.controller;

import bstore.bookstore.dto.user.UserLoginRequestDto;
import bstore.bookstore.dto.user.UserLoginResponseDto;
import bstore.bookstore.dto.user.UserRegistrationRequestDto;
import bstore.bookstore.dto.user.UserResponseDto;
import bstore.bookstore.exception.RegistrationException;
import bstore.bookstore.security.AuthenticationService;
import bstore.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints for user login/registration")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(summary = "register new user")
    public UserResponseDto registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.registerUser(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "login user")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
