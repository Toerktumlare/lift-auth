package se.andolf.lift.auth.api;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.andolf.lift.auth.entities.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Thomas on 2017-07-16.
 */

public class UserPrincipal implements UserDetails {

    private final UserEntity userEntity;

    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getValue().name())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userEntity.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userEntity.isActive();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isActive();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
