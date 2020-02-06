package com.snake19870227.stiger.mall.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bu HuaYang
 */
public abstract class BaseJwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_PREFIX = "Bearer ";

    private final JwtSignKey jwtSignKey;

    public BaseJwtAuthenticationFilter(JwtSignKey jwtSignKey) {
        this.jwtSignKey = jwtSignKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, AuthenticationException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(header) && header.startsWith(AUTHENTICATION_PREFIX)) {

            String jwtToken = header.replace(AUTHENTICATION_PREFIX, "");

            if (StringUtils.hasText(jwtToken)) {
                try {
                    Jws<Claims> jws = Jwts.parser().setSigningKey(jwtSignKey.getSigningKey()).parseClaimsJws(jwtToken);

                    UserDetails userDetails = loadUserDetails(jws.getBody(), header);

                    if (userDetails == null) {
                        throw new BadCredentialsException("未能获取到账户信息");
                    }

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, AuthorityUtils.NO_AUTHORITIES);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } catch (JwtException e) {
                    throw new BadCredentialsException(e.getMessage(), e);
                }
            } else {
                throw new AuthenticationCredentialsNotFoundException("未能从请求头中找到有效的 " + HttpHeaders.AUTHORIZATION + " 信息");
            }

        }
        filterChain.doFilter(request, response);
    }

    protected abstract UserDetails loadUserDetails(Claims claims, String bearerJwtToken);
}
