package bookweb.service.serviceImpl;

import bookweb.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<bookweb.domain.entity.User> user = userRepository.findByEmail(email);


        if(user.isPresent()){
            if(user.get().getEmail().equals(email) ){
                return new org.springframework.security.core.userdetails.User(email, user.get().getPassword(), new ArrayList<>());
            }else{
                throw new UsernameNotFoundException("User not found with username: " + email);
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + email);
    }


}
