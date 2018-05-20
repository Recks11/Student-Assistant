//package com.emmanuelirem.studentassistant.auth;
//
//import lombok.AllArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import javax.validation.constraints.NotNull;
//
//import java.util.Optional;
//
//import static lombok.AccessLevel.PACKAGE;
//import static lombok.AccessLevel.PRIVATE;
//
//@Component
//@AllArgsConstructor(access = PACKAGE)
//@FieldDefaults(level = PRIVATE, makeFinal = true)
//public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//
//    @NotNull
//    UserAuthenticationService authenticationService;
//
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
//
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
//        final Object token = usernamePasswordAuthenticationToken.getCredentials();
//
//        return Optional
//                .ofNullable(token)
//                .map(String::valueOf)
//                .map(s1 -> authenticationService.findByToken(s1).block())
//                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
//    }
//}
