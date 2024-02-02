package com.energizer.auto_uz.config.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.energizer.auto_uz.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHandler = request.getHeader("Authorization");
            if(authHandler != null && authHandler.startsWith("Bearer ")) {
                String token = authHandler.substring(7);
                String email = jwtTokenUtil.getEmail(token);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(new SimpleGrantedAuthority(jwtTokenUtil.getRole(token)))
                ));
            }
        } catch (TokenExpiredException | SignatureVerificationException e) {
            logger.debug(e.getMessage());
        } catch (JWTVerificationException ignored) {}
        filterChain.doFilter(request, response);
    }
    private final JwtTokenUtil jwtTokenUtil;
}
