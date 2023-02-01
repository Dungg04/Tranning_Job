package com.example.warehousemanagementapi.services.imp;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.exceptions.BadRequestException;
import com.example.warehousemanagementapi.exceptions.NotFoundException;
import com.example.warehousemanagementapi.filter.AuthenticationResponse;
import com.example.warehousemanagementapi.models.Account;
import com.example.warehousemanagementapi.models.Role;
import com.example.warehousemanagementapi.repositories.AccountRepository;
import com.example.warehousemanagementapi.services.IAccountService;
//import com.example.warehousemanagementapi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImp implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

//    @Autowired
//    private JwtUtil jwtUtil;

//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
////
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;

    @Override
    public List<Account> getAAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new NotFoundException("Couldn't find a account with id: " + id);
        }
        return account.get();
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findByUserName(accountDTO.getUsername());
        if (account != null) {
            throw new BadRequestException("Already exist account with username: " + accountDTO.getUsername());
        }
        Account newAccount = new Account();
        newAccount.setUserName(accountDTO.getUsername());
        newAccount.setPassword(accountDTO.getPassword());
        return accountRepository.save(newAccount);
    }

    @Override
    public Account editAccount(Integer id, AccountDTO accountDTO) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new NotFoundException("Couldn't find a account with id: " + id);
        }
        if (account.isPresent()) {
            throw new BadRequestException("Already exist account with username: " + accountDTO.getUsername());
        }
        account.get().setUserName(accountDTO.getUsername());
        account.get().setPassword(accountDTO.getPassword());
        return accountRepository.save(account.get());
    }

    @Override
    public Boolean deleteAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new NotFoundException("Couldn't find a account with id: " + id);
        }
        accountRepository.delete(account.get());
        return true;
    }

    @Override
    public Account logIn(AccountDTO accountDTO) {
        Account account = accountRepository.findByUserNameAndPassword(accountDTO.getUsername(), accountDTO.getPassword());
        if (account == null) {
            throw new NotFoundException("Couldn't find a account : " + accountDTO.getUsername());
        }
        return account;
    }

//    @Override
//    public AuthenticationResponse logIn(AccountDTO accountDTO) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    accountDTO.getUsername(), accountDTO.getPassword()
//            ));
//        }catch (BadCredentialsException e) {
//            throw new BadRequestException("Incorrect username or password");
//        }
//
//        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(accountDTO.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        Account user = accountRepository.findByUserName(accountDTO.getUsername());
//        List<String> roles = new ArrayList<>();
////        Set<Role> roleSet = user.getRoles();
////        if(roleSet.size() > 0) {
////            roleSet.forEach(item -> roles.add(item.getName()));
////        }
//        return new AuthenticationResponse(jwt, user.getId().intValue(), user.getUserName(), roles);
//    }


}
