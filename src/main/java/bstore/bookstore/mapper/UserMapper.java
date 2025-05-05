package bstore.bookstore.mapper;

import bstore.bookstore.config.MapperConfig;
import bstore.bookstore.dto.user.UserRegistrationRequestDto;
import bstore.bookstore.dto.user.UserResponseDto;
import bstore.bookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserResponseDto toDto(User savedUser);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
