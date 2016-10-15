package com.pyshankov.hairdresser.security.mobile;


import com.pyshankov.hairdresser.domain.User;
import com.pyshankov.hairdresser.security.mobile.repository.JwtTokenRepository;
import com.pyshankov.hairdresser.security.mobile.repository.JwtUtils;
import com.pyshankov.hairdresser.security.service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pyshankov on 14.10.2016.
 */
@WebFilter(urlPatterns = "/mobile/api/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {



    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

             String header = httpServletRequest.getHeader("Authorization");
             User user;
                try {
                    if (header == null ) {
                        throw new JwtException("No JWT token found in request headers");
                    }
                    user = jwtUtils.parseJwtToken(header);

                    if(jwtTokenRepository.getToken(user.getUserName())== null || !jwtTokenRepository.getToken(user.getUserName()).equals(header)){
                        throw new JwtException("Token corrupted");
                        //TODO: delete token from token repository
                    }
                }catch (JwtException e){
                    throw new AuthenticationServiceException(e.getMessage());
                }

             UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUserName());

             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
             SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}