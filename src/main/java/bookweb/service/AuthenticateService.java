package bookweb.service;

import bookweb.domain.dto.UserAuth;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticateService {
    UserDetails authUser(UserAuth userAuth) throws UsernameNotFoundException;

}
