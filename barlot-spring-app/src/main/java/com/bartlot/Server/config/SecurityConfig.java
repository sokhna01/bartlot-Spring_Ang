package com.bartlot.Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.bartlot.Server.filters.JwtAuthenticationFilter;
import com.bartlot.Server.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/login/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}













// package com.bartlot.Server.config;

// import com.bartlot.Server.filters.TokenFilter;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

// @Configuration
// public class WebConfig {

// @Bean
// public FilterRegistrationBean<TokenFilter> tokenFilterRegistration() {
// FilterRegistrationBean<TokenFilter> registration = new
// FilterRegistrationBean<>();
// registration.setFilter(new TokenFilter());
// registration.addUrlPatterns("/login"); // Ajouter les URL qui doivent être
// filtrées
// return registration;
// }
// }

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
// .disable()
// .authorizeHttpRequests()
// .requestMatchers("/login/*", "/selectListData")
// .permitAll()
// .anyRequest()
// .authenticated()
// .and()
// .sessionManagement();
// return http.build();
// }

// @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws
// Exception {
// auth
// .inMemoryAuthentication()
// .withUser("user").password("{noop}password").roles("USER");
// }
// }