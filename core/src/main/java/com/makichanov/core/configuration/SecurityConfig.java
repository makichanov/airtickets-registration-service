package com.makichanov.core.configuration;

import com.makichanov.core.controller.filter.JwtAccessFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAccessFilter jwtAccessFilter;

    //TODO: вынести строки в константы
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/signup", "/login").permitAll()
                    .antMatchers(HttpMethod.GET, "/tickets", "/tickets/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/destinations").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST, "/destinations").hasAnyRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/destinations").hasAnyRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/tickets").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/tickets/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/flights", "/flights/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST, "/flights").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/flights/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                    .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtAccessFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
