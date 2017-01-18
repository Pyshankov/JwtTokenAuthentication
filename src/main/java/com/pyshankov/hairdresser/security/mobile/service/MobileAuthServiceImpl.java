package com.pyshankov.hairdresser.security.mobile.service;

import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.exception.UserConstraintException;
import com.pyshankov.hairdresser.repository.UserRepository;
import com.pyshankov.hairdresser.security.mobile.domain.LoginUserCredentials;
import com.pyshankov.hairdresser.security.mobile.repository.JwtTokenRepository;
import com.pyshankov.hairdresser.security.mobile.repository.JwtUtils;
import com.pyshankov.hairdresser.security.service.CustomUserDetailsService;
import com.pyshankov.hairdresser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by pyshankov on 16.10.2016.
 */
@Service
public class MobileAuthServiceImpl implements  MobileApiAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenRepository tokenRepository;

    @Autowired
    private UserService userRepository;


    @Override
    public String login(LoginUserCredentials userCredentials) {

        //throws AuthenticationException if invalid credentials has been provided
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCredentials.getUserName(),
                        userCredentials.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userCredentials.getUserName());
        String token;
        if(tokenRepository.getToken(userDetails.getUsername())!=null){
            token = tokenRepository.getToken(userDetails.getUsername());
        }else {
            token = jwtUtils.createJwtToken(userRepository.findByUserName(userDetails.getUsername()));
            tokenRepository.addToken(userDetails.getUsername(),token);
        }
        return token;
    }

    @Override
    public String signup(User user) {
        user.setActivated(true);
        user.setRole(User.Role.USER);
        try {
            user =  userRepository.save(user);
        }catch (RuntimeException e){
            throw  new UserConstraintException("User with this name already exists.");
        }
        String token = jwtUtils.createJwtToken(user);
        tokenRepository.addToken(user.getUserName(),token);
        return token;
    }

    @Override
    public Map<String, String> checkTokenValidity(String token) {
        return null;
    }

    @Override
    public void invalidateToken(String token) {
        String userName = getCurrentPrincipal().getUsername();
        tokenRepository.deleteToken(userName);
    }

    public static UserDetails getCurrentPrincipal(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
