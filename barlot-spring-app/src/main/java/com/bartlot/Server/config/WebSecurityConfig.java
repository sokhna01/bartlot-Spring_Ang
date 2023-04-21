package com.bartlot.Server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/login/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

}

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// @Autowired
// private UserDetailsService userDetailsService;

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http
// .authorizeRequests(authorize -> authorize
// .mvcMatchers("/public/**").permitAll()
// .mvcMatchers("/private/**").hasRole("USER")
// .anyRequest().authenticated())
// .formLogin()
// .and()
// .logout()
// .and()
// .httpBasic();
// }

// @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws
// Exception {
// auth
// .userDetailsService(userDetailsService)
// .passwordEncoder(passwordEncoder());
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }
// }

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {

// @Autowired
// private UserRepository userRepository;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// User user = userRepository.findByUsername(username);
// if (user == null) {
// throw new UsernameNotFoundException("User not found");
// }
// return new
// org.springframework.security.core.userdetails.User(user.getUsername(),
// user.getPassword(), getAuthorities(user.getRoles()));
// }

// private Collection<? extends GrantedAuthority> getAuthorities(Set<Role>
// roles) {
// return roles.stream()
// .map(role -> new SimpleGrantedAuthority(role.getName()))
// .collect(Collectors.toList());
// }
// }
