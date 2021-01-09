package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CreateUserDto;
import bookweb.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Converter<CreateUserDto, User> {
    @Override
    public User convert(CreateUserDto from) {
        User user = new User();

        user.setEmail(from.getEmail());
        user.setName(from.getName());
        user.setSurname(from.getSurname());
        user.setAdmin(from.getAdmin());
        user.setPassword(from.getPassword());

        return user;
    }
}
