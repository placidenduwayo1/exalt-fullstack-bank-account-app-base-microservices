package fr.exalt.businessmicroservicespringsecurity.servicesecurity.securityuserservice;

import fr.exalt.businessmicroservicespringsecurity.entities.models.UserModel;
import fr.exalt.businessmicroservicespringsecurity.exceptions.ExceptionE;
import fr.exalt.businessmicroservicespringsecurity.exceptions.UserNotFoundException;
import fr.exalt.businessmicroservicespringsecurity.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
@Slf4j
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = null;
        try {
            userModel = userRepository.findByUsername(username).orElseThrow(
                    () -> new UserNotFoundException(ExceptionE.USER_NOT_FOUND)
            );
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
        }
        assert userModel !=null;
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        userModel.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        });
        return new org.springframework.security.core.userdetails
                .User(userModel.getUsername(), userModel.getPwd(), authorities);
    }
}
