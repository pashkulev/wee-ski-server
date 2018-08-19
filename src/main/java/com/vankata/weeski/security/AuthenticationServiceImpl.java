package com.vankata.weeski.security;

import com.vankata.weeski.domain.role.Authority;
import com.vankata.weeski.domain.role.Role;
import com.vankata.weeski.domain.role.RoleRepository;
import com.vankata.weeski.domain.user.exception.EmailExistsException;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.domain.user.repository.UserRepository;
import com.vankata.weeski.exception.AppException;
import com.vankata.weeski.payload.ApiResponse;
import com.vankata.weeski.payload.JwtAuthenticationResponse;
import com.vankata.weeski.payload.LoginRequest;
import com.vankata.weeski.payload.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     ModelMapper modelMapper,
                                     AuthenticationManager authenticationManager,
                                     JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        User user = this.userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
//        UserPrincipal userPrincipal = this.modelMapper.map(user, UserPrincipal.class);
        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return new JwtAuthenticationResponse(jwt, userPrincipal);
    }

    @Override
    public ApiResponse register(RegisterRequest registerRequest, MultipartFile imageFile) {
        //todo: try to move this logic to another service or even microservice
        if (imageFile != null) {
            try {
                String imageBasePath = "D:\\PROGRAMMING\\JAVA\\SOFTUNI\\JS_WEB\\ANGULAR_FUNDAMENTALS\\wee-ski-project\\wee-ski-client\\src\\assets\\images\\profilePictures\\";
                String name = imageFile.getOriginalFilename();
                String extension = name.substring(name.lastIndexOf("."));
                String fileName = UUID.randomUUID().toString() + extension;
                String imagePath = imageBasePath + fileName;
                File targetFile = new File(imagePath);
                Files.copy(imageFile.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                registerRequest.setProfilePictureUrl(fileName);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        Optional<User> optionalUser = this.userRepository.findByEmail(registerRequest.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailExistsException();
        }

        User user = this.modelMapper.map(registerRequest, User.class);

        Role userRole = roleRepository.findByAuthority(Authority.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        user.setEnabled(true);

        userRepository.save(user);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/api/users/{username}")
//                .buildAndExpand(result.getUsername()).toUri();

        return new ApiResponse(true, "Registration successful!");
    }
}
