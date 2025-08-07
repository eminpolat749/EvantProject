package com.eminpolat.evantproject.controller;


import com.eminpolat.evantproject.business.requests.LoginRequest;
import com.eminpolat.evantproject.entites.User;
import com.eminpolat.evantproject.dataAccess.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.eminpolat.evantproject.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        // Kullanıcıyı bul, bulamazsan hata kodu olustur.
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));


        // Şifreyi kontrol et
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Şifre hatalı!");
        }

        // Kullanıcı geçerliyse, JWT token üret
        String token = jwtUtil.generateToken(user.getUsername());

        // Token'ı response olarak dön
        return token;
    }

}
