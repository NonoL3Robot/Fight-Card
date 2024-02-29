package fr.dawan.security.interceptors;

import fr.dawan.security.conf.SecurityConfig;
import fr.dawan.security.tools.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    
    private final UserDetailsService service;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getMethod().equals("OPTIONS") // On ne bloque jamais une requête OPTIONS
                && isInterceptedURI(request.getRequestURI())) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Invalid Authorization");
            }
            String token = authHeader.substring(7); // Retire "Bearer "
            try {
                String username = JwtUtils.extractUsername(token);
                if (username != null) {
                    UserDetails userDetails = service.loadUserByUsername(username);
                    // À partir de ce stade, le token JWT a rempli sa fonction (transmettre un 'username' pour identifier un utilisateur en base)
                    // Il faut à présent un token utilisable par SpringSecurity
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                throw new ServletException("Invalid token");
            }
        }
        filterChain.doFilter(request, response);
    }
    
    private boolean isInterceptedURI(String uri) {
        return Arrays.stream(SecurityConfig.AUTHORIZED_URL)
                .map(url -> url.replace("**", ".*"))
                .noneMatch(uri::matches);
    }
}
