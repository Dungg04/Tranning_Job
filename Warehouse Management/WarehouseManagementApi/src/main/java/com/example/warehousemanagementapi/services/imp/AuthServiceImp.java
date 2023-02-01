package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.exceptions.BadRequestException;
import com.example.warehousemanagementapi.filter.AuthenticationResponse;
import com.example.warehousemanagementapi.models.Account;
import com.example.warehousemanagementapi.models.Role;
import com.example.warehousemanagementapi.payload.AuthenticationRequest;
import com.example.warehousemanagementapi.repositories.AccountRepository;
import com.example.warehousemanagementapi.repositories.RoleRepository;
import com.example.warehousemanagementapi.services.IAuthService;
import com.example.warehousemanagementapi.utils.Convert;
import com.example.warehousemanagementapi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImp implements IAuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect username or password");
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        Account user = accountRepository.findByUserName(authenticationRequest.getUsername());
        List<String> roles = new ArrayList<>();
        Set<Role> roleSet = (Set<Role>) user.getRole();
        if (roleSet.size() > 0) {
            roleSet.forEach(item -> roles.add(item.getName()));
        }
        return new AuthenticationResponse(jwt, user.getId(), user.getUserName(), roles);
    }

    @Override
    public AuthenticationResponse signupUser(AccountDTO accountDTO) {
        Account account = accountRepository.findByUserName(accountDTO.getUsername());
        if (account != null) {
            throw new BadRequestException("Username exists");
        }
        Account accountNew = new Account();
        accountNew.setUserName(accountDTO.getUsername());
        accountNew.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        accountNew.setRole(Set.of(role));
        Account account1 = accountRepository.save(accountNew);
        Set<Account> accounts =  role.getAccounts();
        accounts.add(accountNew);
        role.setAccounts(accounts);
        roleRepository.save(role);

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(account1.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, account1.getId(), account1.getUserName(), List.of(role.getName()));
    }

    @Override
    public AuthenticationResponse signupAdmin(AccountDTO accountDTO) {
        Account account = accountRepository.findByUserName(accountDTO.getUsername());
        if (account != null) {
            throw new BadRequestException("Username exists");
        }
        Account accountNew = new Account();
        accountNew.setUserName(accountDTO.getUsername());
        accountNew.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        accountNew.setRole(Set.of(role));
        Account account1 = accountRepository.save(accountNew);
        Set<Account> accounts =  role.getAccounts();
        accounts.add(accountNew);
        role.setAccounts(accounts);
        roleRepository.save(role);

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(account1.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, account1.getId(), account1.getUserName(), List.of(role.getName()));
    }

    @Override
    public AuthenticationResponse validateToken(AuthenticationResponse authenticationResponse) throws InvalidObjectException {
        try {
            String jwt = authenticationResponse.getJwt();
            String username = jwtUtil.extractUsername(jwt);
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            Account account = accountRepository.findByUserName(username);
            Set<Role> roles = (Set<Role>) account.getRole();
            return new AuthenticationResponse(
                    jwtUtil.generateToken(userDetails),
                    account.getId(),
                    account.getUserName(),
                    roles.stream().map(Role::getName).collect(Collectors.toList())
            );
        } catch (Exception e) {
            throw new InvalidObjectException(e.getMessage());
        }
    }
}
