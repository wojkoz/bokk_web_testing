package bookweb.service.serviceImpl;

import bookweb.domain.dto.UserAuth;
import bookweb.domain.entity.User;
import bookweb.domain.repository.UserRepository;
import bookweb.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticateServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails authUser(UserAuth userAuth) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByEmail(userAuth.getLogin());

        if(user.isPresent()){
            if(user.get().getEmail().equals(userAuth.getLogin()) && user.get().getPassword().equals(userAuth.getPassword())){
                return new org.springframework.security.core.userdetails.User(userAuth.getLogin(), userAuth.getPassword(), new ArrayList<>());
            }else{
                throw new UsernameNotFoundException("User not found with username: " + userAuth.getLogin());
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + userAuth.getLogin());
    }

}
