package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.UserDto;
import bookweb.domain.entity.User;
import org.springframework.stereotype.Component;

import static bookweb.domain.mapper.UserListMapper.getUserDto;

@Component
public class UserDtoMapper implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User from) {
        return getUserDto(from);
    }
}
