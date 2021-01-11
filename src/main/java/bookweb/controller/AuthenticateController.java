package bookweb.controller;

import bookweb.config.JwtTokenUtil;
import bookweb.domain.dto.JwtResponse;
import bookweb.domain.dto.UserAuth;
import bookweb.service.AuthenticateService;
import bookweb.service.serviceImpl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl service;
    private final AuthenticateService authService;


    @Autowired
    public AuthenticateController(JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl service, AuthenticateService authService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.service = service;
        this.authService = authService;
    }


    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authUser(@RequestBody UserAuth userAuth) {
        LOGGER.info("auth user: {}", userAuth.getLogin());
        try {
            UserDetails userDetails = authService.authUser(userAuth);
            String token = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
