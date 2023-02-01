package com.example.warehousemanagementapi.controller;

import com.example.warehousemanagementapi.bases.BaseController;
import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.filter.AuthenticationResponse;
import com.example.warehousemanagementapi.payload.AuthenticationRequest;
import com.example.warehousemanagementapi.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.InvalidObjectException;

@RestController
@RequestMapping("v1/api/auth")
public class AuthController extends BaseController<AuthenticationResponse> {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        return this.resSuccess(authService.login(authenticationRequest));
    }

    @PostMapping("/signup-user")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> signupUser(@RequestBody AccountDTO accountDTO){
        return this.resSuccess(authService.signupUser(accountDTO));
    }
    @PostMapping("/signup-admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> signupAdmin(@RequestBody AccountDTO accountDTO){
        return this.resSuccess(authService.signupAdmin(accountDTO));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody AuthenticationResponse authenticationResponse) throws InvalidObjectException {
        return this.resSuccess(authService.validateToken(authenticationResponse));
    }
}

