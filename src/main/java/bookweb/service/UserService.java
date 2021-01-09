package bookweb.service;

import bookweb.domain.dto.CreateUserDto;
import bookweb.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAll();
    Optional<UserDto> getById(Long id);
    Optional<UserDto> getByEmail(String email);
    UserDto createUser(CreateUserDto createUserDto);
    Optional<UserDto> deleteById(Long id);
    Optional<UserDto> deleteByEmail(String email);
    boolean checkIfEmailExists(String email);
    void banUser(Long id);

    UserDto updateUser(UserDto createUserDto);

}
