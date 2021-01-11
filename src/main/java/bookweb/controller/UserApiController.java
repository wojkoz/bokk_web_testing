package bookweb.controller;

import bookweb.domain.dto.CreateUserDto;
import bookweb.domain.dto.UserDto;
import bookweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
public class UserApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        LOGGER.info("find all users");

        List<UserDto> userDtoList = userService.getAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/users/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        LOGGER.info("create user: {}", createUserDto);
        boolean emailExists = userService.checkIfEmailExists(createUserDto.getEmail());

        if(!emailExists){
            UserDto userDto =  userService.createUser(createUserDto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new UserDto(), HttpStatus.CONFLICT);
        }

    }

    @PutMapping(value = "/users/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto updateUser) {
        LOGGER.info("update user: {}", updateUser);
        boolean emailExists = userService.checkIfEmailExists(updateUser.getEmail());

        if(emailExists){
            UserDto userDto =  userService.updateUser(updateUser);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new UserDto(), HttpStatus.CONFLICT);
        }

    }


    @GetMapping(value = "/users/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        LOGGER.info("find  user by id: {}", id);

        Optional<UserDto> userDto =  userService.getById(id);
        return userDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserDto(), HttpStatus.NO_CONTENT));

    }

    @GetMapping(value = "/users/email/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
        LOGGER.info("find  user by email: {}", email);

        Optional<UserDto> userDto =  userService.getByEmail(email);
        return userDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserDto(), HttpStatus.NO_CONTENT));

    }

    @DeleteMapping(value = "/users/email/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> deleteUserByEmail(@PathVariable("email") String email) {
        LOGGER.info("delete  user by email: {}", email);

        Optional<UserDto> userDto =  userService.deleteByEmail(email);
        return userDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserDto(), HttpStatus.NO_CONTENT));

    }

    @DeleteMapping(value = "/users/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> deleteUserById(@PathVariable("id") Long id) {
        LOGGER.info("delete  user by id: {}", id);

        Optional<UserDto> userDto =  userService.deleteById(id);
        return userDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserDto(), HttpStatus.NO_CONTENT));

    }

    @PostMapping(value = "/user-ban/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> banUser(@PathVariable Long id) {
        LOGGER.info("ban user: {}", id);

        userService.banUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
