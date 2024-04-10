package apiBoschEsy.apiInSpringBoot.service;

import apiBoschEsy.apiInSpringBoot.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Create a component (Component type Service). The Spring go will reload the file (@Service)
public class AuthenticationService implements UserDetailsService {
    // Sem essa classe implementada, o Spring Security não cria ela automática
    @Autowired
    private IRepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryUser.findByLogin(username);
    }
}
