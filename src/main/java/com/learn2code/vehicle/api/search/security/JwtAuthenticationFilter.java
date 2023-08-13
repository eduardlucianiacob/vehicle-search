package com.learn2code.vehicle.api.search.security;

import com.learn2code.vehicle.api.search.config.MyUserDetails;
import com.learn2code.vehicle.api.search.config.MyUserDetailsService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");

        String jwtToken = null;
        String username = null;

        if(authToken != null && authToken.startsWith("Bearer ")){
            jwtToken = authToken.substring(7);
            username = jwtUtil.extractUsername(jwtToken);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }


        filterChain.doFilter(request, response);
    }
}
