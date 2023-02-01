package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.models.Account;
import com.example.warehousemanagementapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user by username: " + username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRole().forEach(item -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(item.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }

}