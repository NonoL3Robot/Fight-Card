package fr.dawan.auth.filters;

import fr.dawan.auth.conf.SecurityConfig;
import fr.dawan.auth.tools.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    
    private final UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        if (!request.getMethod().equals("OPTIONS") && isInterceptedRequest(request)) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            try {
                if (authHeader == null || (!authHeader.startsWith("Bearer") && !authHeader.startsWith("bearer")))
                    throw new ServletException("Invalid authorization"); // prévoir une gestion de l'exception =>
                
                String jwtToken = authHeader.substring(7); // retirer le mot bearer du token
                String username = JwtUtils.extractUsername(jwtToken);
                if (username != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (Boolean.TRUE.equals(JwtUtils.validateToken(jwtToken, userDetails))) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, jwtToken, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else
                        throw new ServletException("Invalid token");
                    
                }
            } catch (ExpiredJwtException | ServletException ex) {
                ex.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }
    
    private boolean isInterceptedRequest(HttpServletRequest request) {
        String URI = request.getRequestURI();
        return Stream.concat(
                        Arrays.stream(SecurityConfig.AUTHORIZED_BY_METHOD.getOrDefault(HttpMethod.valueOf(request.getMethod()), new String[]{})),
                        Arrays.stream(SecurityConfig.AUTHORIZED_URL))
                .map(s -> s.replace("**", ".*")).noneMatch(URI::matches);
    }
}
