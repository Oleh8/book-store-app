package bstore.bookstore.service;

import bstore.bookstore.dto.user.UserRegistrationRequestDto;
import bstore.bookstore.dto.user.UserResponseDto;
import bstore.bookstore.exception.RegistrationException;
import bstore.bookstore.mapper.UserMapper;
import bstore.bookstore.model.User;
import bstore.bookstore.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException {

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Can't register user with email, email already exists: "
                    + requestDto.getEmail());
        }

        User user = userMapper.toModel(requestDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
