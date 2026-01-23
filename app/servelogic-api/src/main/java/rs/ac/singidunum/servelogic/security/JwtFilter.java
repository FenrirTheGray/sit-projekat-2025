package rs.ac.singidunum.servelogic.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.ac.singidunum.servelogic.service.JWTService;
import rs.ac.singidunum.servelogic.service.ServelogicUserDetailsService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) filterChain.doFilter(request, response);;

        String token = authHeader.substring(7);
        String username = jwtService.getUsernameFromToken(token);

        if(username == null) filterChain.doFilter(request, response);;
        if(SecurityContextHolder.getContext().getAuthentication() != null) filterChain.doFilter(request, response);;

        UserDetails userDetails = context.getBean(ServelogicUserDetailsService.class).loadUserByUsername(username);

        if(!jwtService.validateToken(token, userDetails)) filterChain.doFilter(request, response);;

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
