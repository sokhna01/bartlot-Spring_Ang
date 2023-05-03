package com.bartlot.Server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bartlot.Server.entity.CompanyUsersEntity;
import com.bartlot.Server.repository.CompanyUsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CompanyUsersRepository companyUsersRepository;

    public CustomUserDetailsService(CompanyUsersRepository companyUsersRepository) {
        this.companyUsersRepository = companyUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CompanyUsersEntity companyUser = companyUsersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // if (companyUser.isAdmin()) {
        // authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // }

        return new User(companyUser.getUsername(), companyUser.getPassword(), authorities);
    }
}
