package mateusz.michal.chat.Component;

import mateusz.michal.chat.Model.User;
import mateusz.michal.chat.Repository.UserRepository;
import mateusz.michal.chat.Service.CookieService;
import mateusz.michal.chat.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private List<String> notFilterEndpoints = Arrays.asList("/api/register","/api/user","/api/authenticate");

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    CookieService cookieService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie tokenCookie = cookieService.getTokenCookieFromCookies(request.getCookies());
        if(tokenCookie != null) {
            String username = jwtTokenUtil.getUsernameFromToken(tokenCookie.getValue());
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.decodeAndVerifyJwt(tokenCookie.getValue(), userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return notFilterEndpoints.stream().anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
    }
}
