package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.auth.AuthenticationRequest;
import edu.maszek.brainpowerquiz.auth.AuthenticationResponse;
import edu.maszek.brainpowerquiz.auth.RegisterRequest;
import edu.maszek.brainpowerquiz.exception.AuthenticationException;
import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import edu.maszek.brainpowerquiz.repository.RoleRepository;
import edu.maszek.brainpowerquiz.repository.TokenRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import edu.maszek.brainpowerquiz.token.Token;
import edu.maszek.brainpowerquiz.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws AuthenticationException {
        String name = request.getUsername();
        Optional<UserEntity> userOptional = userRepository.findByUsername(name);
        if(userOptional.isPresent()) throw new AuthenticationException(AuthenticationException.AlreadyExists(name));
        else {
            UserEntity userEntity = UserEntity.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .birthYear(request.getBirthYear())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(roleRepository.findByName("USER"))
                    .build();
            userRepository.save(userEntity);
            var jwtToken = jwtService.generateToken(userEntity);
            saveUserToken(userEntity, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity user) {
        List<Token> tokens = tokenRepository.findAll();
        List<Token> validUserTokens = tokens.stream().filter(token ->
                token.getUser().get_id().equals(user.get_id()) &&
                        (!token.expired || !token.revoked)).collect(Collectors.toList());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
