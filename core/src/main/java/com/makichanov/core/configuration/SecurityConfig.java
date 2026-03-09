package com.makichanov.core.configuration;

import com.makichanov.core.filter.AuditFilter;
import com.makichanov.core.filter.JwtAccessFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAccessFilter jwtAccessFilter;
    private final AuditFilter auditFilter;

    //TODO: вынести строки в константы
    // TODO: 7/26/22 Вынести строки в константы
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/signup", "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/tickets", "/tickets/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/destinations").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/destinations").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/destinations").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/tickets").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/tickets/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/flights", "/flights/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/flights").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/flights/**").hasRole("ADMIN")
                // Swagger / OpenAPI
                .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                // All other requests
                .anyRequest().authenticated()
            )

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .addFilterBefore(jwtAccessFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(auditFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
