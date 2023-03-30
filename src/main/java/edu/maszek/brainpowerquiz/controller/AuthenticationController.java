package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.auth.AuthenticationRequest;
import edu.maszek.brainpowerquiz.auth.AuthenticationResponse;
import edu.maszek.brainpowerquiz.auth.RegisterRequest;
import edu.maszek.brainpowerquiz.exception.AuthenticationException;
import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/regist")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Valid RegisterRequest request)
            throws AuthenticationException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
            throws AuthenticationException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
