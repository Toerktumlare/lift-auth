package se.andolf.lift.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.andolf.lift.auth.api.AccountInfoEntity;
import se.andolf.lift.auth.repositories.AccountRepository;

import java.util.Optional;

import static java.util.Optional.*;

/**
 * Created by stephan on 20.03.16.
 */
@Service
@Transactional
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return ofNullable(accountRepository.findByUsername(username)).map(account -> new User(
                account.getUsername(), account.getPassword(),
                        account.isEnabled(), account.isEnabled(), account.isEnabled(), account.isEnabled(),
                        AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
    }
}
