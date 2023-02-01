package com.example.warehousemanagementapi.services;

import com.example.warehousemanagementapi.dtos.AccountDTO;
import com.example.warehousemanagementapi.filter.AuthenticationResponse;
import com.example.warehousemanagementapi.payload.AuthenticationRequest;

import java.io.InvalidObjectException;

public interface IAuthService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    AuthenticationResponse signupUser(AccountDTO accountDTO);
    AuthenticationResponse signupAdmin(AccountDTO accountDTO);
    AuthenticationResponse validateToken(AuthenticationResponse authenticationResponse) throws InvalidObjectException;
}
