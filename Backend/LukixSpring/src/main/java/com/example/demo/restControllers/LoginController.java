package com.example.demo.restControllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ErrResponse;
import com.example.demo.dtos.LoginReq;
import com.example.demo.dtos.Response;
import com.example.demo.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import model.User;

@RestController
@RequestMapping("rest/auth/")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    
    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @ResponseBody
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            User user = new User();
            user.setUsername(email);
            String token = jwtUtil.createToken(user);
            Response loginRes = new Response();
            loginRes.setEmail(email);
            loginRes.setToken(token);
            if (email.contains("admin"))
            	loginRes.setAdmin(true);
            else loginRes.setAdmin(false);
            System.out.println(loginRes.getAdmin());

            return ResponseEntity.ok(loginRes);

        }catch (Exception e){
            ErrResponse errorResponse = new ErrResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
        }
        
        // Očisti sesiju (ako koristiš sesije)
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
}
