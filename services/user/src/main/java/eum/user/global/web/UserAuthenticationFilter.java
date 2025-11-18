package eum.user.global.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class UserAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_ACCESS_TOKEN = "access_token";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // TODO: 실제 구현 시 JWT 파싱으로 userId/roles 추출
            String userId = token;
            Authentication auth = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
            ((UsernamePasswordAuthenticationToken) auth).setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_ACCESS_TOKEN);
        if (token != null && !token.isBlank()) {
            return parseBearer(token);
        }
        String auth = request.getHeader(HEADER_AUTHORIZATION);
        if (auth != null && auth.startsWith("Bearer ")) {
            return parseBearer(auth.substring(7));
        }
        return null;
    }

    private String parseBearer(String raw) {
        return raw.replace("Bearer ", "").trim();
    }
}
