package com.sistema_de_alertas.Sistema_de_Alertas.auth.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sistema_de_alertas.Sistema_de_Alertas.auth.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;


public class JwtTokenValidator extends OncePerRequestFilter {


    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null){
            jwtToken = jwtToken.substring(7);
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            String mail = jwtUtils.extractUserName(decodedJWT);

            String stringAuthorities = jwtUtils.returnClaim(decodedJWT,"authorities").asString();
            
           GrantedAuthority authority = new SimpleGrantedAuthority(stringAuthorities);

           Collection<GrantedAuthority> authorities = Collections.singleton(authority);

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(mail,null,authorities);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);


        }
        filterChain.doFilter(request,response);
    }
}
