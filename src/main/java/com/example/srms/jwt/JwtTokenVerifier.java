package com.example.srms.jwt;

import com.example.srms.userService.UserDetailServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenVerifier extends OncePerRequestFilter {

    @Autowired
    JWTUtils  jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try{
             String jwt =  parseJwt(request);
             if(jwt != null && jwtUtils.validateJwtToken(jwt)){
                 String username = jwtUtils.getUsernameFromJwtToken(jwt);

                 UserDetails  userDetails =  userDetailService.loadUserByUsername(jwt);
                 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                         userDetails, null,userDetails.getAuthorities());

                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                 SecurityContextHolder.getContext().setAuthentication(authentication);
             }
        }
        catch (Exception e){
            logger.error("Can not set user authentication: {} ", e);
        }

        filterChain.doFilter(request,response);

    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth =  request.getHeader("Authorization");

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
}
