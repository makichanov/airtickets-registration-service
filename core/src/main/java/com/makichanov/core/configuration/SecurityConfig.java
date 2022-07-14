package com.makichanov.core.configuration;

import com.makichanov.core.controller.filter.JwtAccessFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TODO: 7/14/22 РАЗДЕЛЕМ ЛОГИЧЕСКИЕ БЛОКИ ПУСТЫМИ СТРОКАМИ
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// TODO: 7/14/22 Почему юзаешь deprecated класс? Должны быть альтернативы.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAccessFilter jwtAccessFilter;

    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                //TODO: вынести строки в константы
                    .antMatchers(HttpMethod.POST, "/signup", "/login").permitAll()
                    .antMatchers(HttpMethod.GET, "/tickets", "/tickets/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/tickets").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/tickets").hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
                .and()
                // TODO: 7/14/22 Одна строчка - одна точка
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtAccessFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
