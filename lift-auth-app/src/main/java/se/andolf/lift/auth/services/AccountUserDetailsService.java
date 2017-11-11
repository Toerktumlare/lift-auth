package se.andolf.lift.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.andolf.lift.auth.api.UserPrincipal;
import se.andolf.lift.auth.repositories.UserRepository;

/**
 * Created by stephan on 20.03.16.
 */
@Service
@Transactional
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserName(username).map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("Not Found"));
    }
}
