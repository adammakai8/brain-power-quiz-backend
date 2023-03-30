package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.auth.AuthenticationRequest;
import edu.maszek.brainpowerquiz.auth.AuthenticationResponse;
import edu.maszek.brainpowerquiz.auth.RegisterRequest;
import edu.maszek.brainpowerquiz.exception.AuthenticationException;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request) throws AuthenticationException;
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException;
}
