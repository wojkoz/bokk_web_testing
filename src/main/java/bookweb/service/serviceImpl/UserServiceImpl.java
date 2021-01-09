package bookweb.service.serviceImpl;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.CreateUserDto;
import bookweb.domain.dto.UserDto;
import bookweb.domain.entity.BannedUser;
import bookweb.domain.entity.User;
import bookweb.domain.repository.BannedUserRepository;
import bookweb.domain.repository.UserRepository;
import bookweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Converter<User, UserDto> userDtoMapper;
    private final Converter<List<User>, List<UserDto>> userListMapper;
    private final Converter<CreateUserDto, User> userMapper;
    private final UserRepository userRepository;
    private final BannedUserRepository bannedUserRepository;

    @Autowired
    public UserServiceImpl(Converter<User, UserDto> userDtoMapper,
                           Converter<List<User>, List<UserDto>> userListMapper,
                           Converter<CreateUserDto, User> userMapper,
                           UserRepository userRepository, BannedUserRepository bannedUserRepository) {
        this.userDtoMapper = userDtoMapper;
        this.userListMapper = userListMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.bannedUserRepository = bannedUserRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userListMapper.convert(userRepository.findAll());
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userDtoMapper::convert);
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userDtoMapper::convert);
    }

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.convert(createUserDto);
        userRepository.save(user);

        return userDtoMapper.convert(user);
    }

    @Override
    public Optional<UserDto> deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            bannedUserRepository.deleteById(id);
            userRepository.deleteById(user.get().getUserId());
            return Optional.of(userDtoMapper.convert(user.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDto> deleteByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            bannedUserRepository.deleteById(user.get().getUserId());
            userRepository.deleteById(user.get().getUserId());
            return Optional.of(userDtoMapper.convert(user.get()));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void banUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            BannedUser bannedUser = new BannedUser();
            bannedUser.setBannedUserId(id);
            bannedUser.setBanDate(Timestamp.from(Instant.now()));
            bannedUser.setReason("Tak");

            bannedUserRepository.save(bannedUser);
        }
    }

    @Override
    public UserDto updateUser(UserDto updateUser) {
        Optional<User> user = userRepository.findById(updateUser.getUserId());
        if(user.isPresent()){
            User u = user.get();
            u.setName(updateUser.getName());
            u.setSurname(updateUser.getSurname());
            u.setPassword(updateUser.getPassword());
            u.setEmail(updateUser.getEmail());

            userRepository.save(u);

            return userDtoMapper.convert(u);
        }
        return new UserDto();
    }

}
