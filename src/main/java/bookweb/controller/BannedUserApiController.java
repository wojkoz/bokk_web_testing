package bookweb.controller;

import bookweb.domain.dto.BannedUserDto;
import bookweb.service.BannedUserService;
import bookweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class BannedUserApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BannedUserApiController.class);

    private final BannedUserService bannedUserService;
    private final UserService userService;

    @Autowired
    public BannedUserApiController(BannedUserService bannedUserService, UserService userService) {
        this.bannedUserService = bannedUserService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(value = "/banned-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BannedUserDto>> getBannedUsers() {
        LOGGER.info("find all banned users");

        List<BannedUserDto> bannedUserDtoList = bannedUserService.findAll();
        return new ResponseEntity<>(bannedUserDtoList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/banned-user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BannedUserDto> getBannedUserById(@PathVariable Long id) {
        LOGGER.info("request banned user with id: {}",id);

        Optional<BannedUserDto> bannedUserDto = bannedUserService.findById(id);
        return bannedUserDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

}
