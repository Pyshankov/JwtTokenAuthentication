package com.pyshankov.hairdresser.security.service;


import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by pyshankov on 14.10.2016.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(username,userRepository);
    }

    public static class CustomUserDetails implements UserDetails {

        private final SimpleGrantedAuthority USER_ROLE = new SimpleGrantedAuthority("ROLE_USER");
        private final SimpleGrantedAuthority USER_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

        private final Collection<? extends GrantedAuthority> ROLES_USER =
                Collections.singletonList(USER_ROLE);
        private final Collection<? extends GrantedAuthority> ROLES_USER_AND_ADMIN =
                Arrays.asList(USER_ROLE, USER_ADMIN);

        private User user;
        private final Collection<? extends GrantedAuthority> roles;

        public CustomUserDetails(final String username,UserRepository userRepository) {
            user = userRepository.findByUserName(username);
            roles = user.getRole()== User.Role.ADMIN ? ROLES_USER_AND_ADMIN : ROLES_USER;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return roles;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUserName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.isActivated();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

}