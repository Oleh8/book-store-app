package bstore.bookstore.service;

import bstore.bookstore.dto.user.UserRegistrationRequestDto;
import bstore.bookstore.dto.user.UserResponseDto;
import bstore.bookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
