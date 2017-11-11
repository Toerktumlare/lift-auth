package se.andolf.lift.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import se.andolf.lift.auth.api.UserPrincipal;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Thomas on 2017-11-11.
 */
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    private static final String SUBJECT = "subject";

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        final UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        response.put(USERNAME, authentication.getName());
        response.put(SUBJECT, userPrincipal.getUserEntity().getId());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
