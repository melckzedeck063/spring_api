package com.example.srms.jwt;

import com.example.srms.userService.UserDetailImpl;
import com.example.srms.userService.UserDetailServiceImpl;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Autowired
    private JWTUtils  jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);

            if(jwt != null && jwtUtils.validateJwtToken(jwt)){
                String username =  jwtUtils.getUsernameFromJwtToken(jwt);

                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        catch (Exception e){
            logger.error("Can not set user authentication : {} " + e.getLocalizedMessage());
        }

        filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth =   request.getHeader("Authorization");

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")){
            return headerAuth.replace("Bearer ", "").trim();
        }
        return null;
    }
}
