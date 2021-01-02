package mateusz.michal.chat.Configuration;

import mateusz.michal.chat.Component.JwtFilter;
import mateusz.michal.chat.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfiguration()).and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/authenticate","/api/register","/api/user").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/chat","/api/logout").authenticated()
                .anyRequest().denyAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin().disable();
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                        .logoutUrl("/api/logout")
                        .logoutSuccessUrl("/api/authenticate")
                        .addLogoutHandler((request, response, auth) -> {
                            Cookie[] cookies = request.getCookies();
                            if(cookies == null){
                                try {
                                    response.sendError(403);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                for (Cookie cookie : request.getCookies()) {
                                    String cookieName = cookie.getName();
                                    Cookie cookieToDelete = new Cookie(cookieName, null);
                                    cookieToDelete.setMaxAge(0);
                                    cookieToDelete.setHttpOnly(true);
                                    cookieToDelete.setSecure(false);
                                    cookieToDelete.setPath("/");
                                    response.addCookie(cookieToDelete);
                                }
                            }
                        })
                );
    }

    @Bean
    CorsConfigurationSource corsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8000"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
