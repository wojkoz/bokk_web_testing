package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.UserDto;
import bookweb.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserListMapper implements Converter<List<User>, List<UserDto>> {
    @Override
    public List<UserDto> convert(List<User> from) {
        return from.stream().map(UserListMapper::getUserDto).collect(Collectors.toList());
    }

    static UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setAdmin(user.getAdmin());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setUserId(user.getUserId());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
