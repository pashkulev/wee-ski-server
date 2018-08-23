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
import com.vankata.weeski.util.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String PROFILE_PICTURES_FOLDER = "users\\profilePictures\\";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     FileService fileService, ModelMapper modelMapper,
                                     AuthenticationManager authenticationManager,
                                     JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.fileService = fileService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return new JwtAuthenticationResponse(jwt, userPrincipal);
    }

    @Override
    public ApiResponse register(RegisterRequest registerRequest, MultipartFile imageFile) {
        if (imageFile != null) {
            String fileName = this.fileService.uploadFile(imageFile, PROFILE_PICTURES_FOLDER);
            registerRequest.setProfilePicture(fileName);
        }

        Optional<User> optionalUser = this.userRepository.findByEmail(registerRequest.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailExistsException();
        }

        User user = this.modelMapper.map(registerRequest, User.class);
        Role userRole = this.roleRepository.findByAuthority(Authority.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singletonList(userRole));
        user.setEnabled(true);

        this.userRepository.save(user);

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/api/users/{username}")
//                .buildAndExpand(result.getUsername()).toUri();

        return new ApiResponse(true, "Registration successful!");
    }
}