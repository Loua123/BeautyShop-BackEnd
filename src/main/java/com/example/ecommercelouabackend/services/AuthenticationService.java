package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.configuration.JwtService;
import com.example.ecommercelouabackend.dto.Mail;
import com.example.ecommercelouabackend.dto.RegisterSellerRequest;
import com.example.ecommercelouabackend.entities.*;
import com.example.ecommercelouabackend.repositories.StoreRepo;
import com.example.ecommercelouabackend.repositories.TokenRepo;
import com.example.ecommercelouabackend.repositories.UserRepo;
import com.example.ecommercelouabackend.services.interfaces.EmailService;
import com.example.ecommercelouabackend.utils.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final StoreRepo storeRepo;
    private final TokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService ;

    public AuthenticationResponse register(RegisterRequest request) {
        String email = request.getUsername();
        String resetCode = UserCode.getCode();
        Mail mail =new Mail(email, resetCode);
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .image(request.getImage())
                .adress(request.getAdress())
                .cin(request.getCin())
                .city(request.getCity())
                .active_code(resetCode)
                .password(passwordEncoder.encode(request.getPassword()))
                .telephone(request.getTelephone())
                .role(Role.CLIENT)
                .isActive(false)
                .build();
        var savedUser = userRepo.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        var jwtToken = jwtService.generateToken(extraClaims, user);
        saveUSerToken(savedUser, jwtToken);
        emailService.sendActivationCodeByMail(mail);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse registerSeller(RegisterSellerRequest request) {
        String email = request.getUsername();
        String resetCode = UserCode.getCode();
        Mail mail =new Mail(email, resetCode);
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .image(request.getImage())
                .adress(request.getAdress())
                .cin((int) request.getCin())
                .city(request.getCity())
                .active_code(resetCode)
                .password(passwordEncoder.encode(request.getPassword()))
                .telephone((int) request.getTelephone())
                .role(Role.CLIENT)
                .isActive(false)
                .build();
        var savedUser = userRepo.save(user);
        Store store = new Store();
        store.setUser(savedUser); // Set the User for the Store
        store.setName(request.getNameStore());
        store.setAdresse(request.getAdress());
        store.setVille(request.getVilleStore());
        store.setCodepostal(request.getCodepostalStore());
        store.setOwnerDOB(request.getOwnerDOB());
        store.setImg_de_piece(request.getImg_de_pieceStore());
        store.setMatriculefiscale(request.isMatriculefiscaleStore());
        store.setValueOfmatriculefiscale(request.getValueOfmatriculefiscaleStore());
        store.setStatus(false);
        storeRepo.save(store);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        var jwtToken = jwtService.generateToken(extraClaims, user);
        saveUSerToken(savedUser, jwtToken);
        emailService.sendActivationCodeByMail(mail);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("********************************************");
        System.out.println("********************************************");
        System.out.println("********************************************");
        var user = userRepo.findByUsername(request.getEmail())
                .orElseThrow(() -> new RuntimeException("USer Not Found"));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        var jwtToken = jwtService.generateToken(extraClaims, user);
        revokeAllUserTokens(user);
        saveUSerToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

    private void revokeAllUserTokens(User savedUser) {
        List<Token> allValidTokenByUser = tokenRepo.findAllValidTokenByUser(savedUser.getId());
        if (allValidTokenByUser.isEmpty())
            return;
        allValidTokenByUser.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(allValidTokenByUser);
    }


    private void saveUSerToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }
}
