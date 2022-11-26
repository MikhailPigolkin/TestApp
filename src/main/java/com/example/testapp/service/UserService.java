package com.example.testapp.service;

import com.example.testapp.entity.User;
import com.example.testapp.entity.enums.Roles;
import com.example.testapp.repository.MessageRepository;
import com.example.testapp.repository.UserRepository;
import com.example.testapp.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    // регистрация
    public ResponseEntity createUser(SignUpRequest userIn) {

        User user = new User();
        user.setName(userIn.getName());
        user.setUserPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));
        //назначаем роль по-умолчанию
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.ROLE_USER);
        user.setRoles(roles);

        try {
            userRepository.save(user);
            return new ResponseEntity(new String[]{"Пользователь создан"}, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Ошибка создания пользователя {}", ex.getMessage());
            return new ResponseEntity(new String[]{"Ошибка при сохранении учётной записи"}, HttpStatus.OK);
        }
    }

    // авторизация
    public ResponseEntity authenticateUser(LoginRequest loginRequest) {

        //проверка, есть ли в базе пользователь с таким же именем
        if (getUserByName(loginRequest.getName()) != null) {
            User user = getUserByName(loginRequest.getName());
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getName(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        long expirationTime = SecurityConstants.EXPIRATION_TIME;

        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication, expirationTime);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(jwt));
    }

    // найти по имени (в ТЗ именно по имени)
    public User getUserByName(String userName) {
        Optional<User> user = userRepository.findUserByName(userName);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}
