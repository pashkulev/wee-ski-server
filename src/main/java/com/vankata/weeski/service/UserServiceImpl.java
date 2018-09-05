package com.vankata.weeski.service;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.role.model.Role;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.exception.ResourceNotFoundException;
import com.vankata.weeski.payload.ApiResponse;
import com.vankata.weeski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User findById(String id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        return optionalUser.get();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User", "email", email);
        }

        return optionalUser.get();
    }

    @Override
    public ApiResponse setUserAuthorities(String userId, Authority[] authorities) {
        List<Role> roles = this.roleService.findByAuthorities(authorities);
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User", "id", userId);
        }

        User user = optionalUser.get();
        user.setRoles(roles);
        this.userRepository.save(user);

        return new ApiResponse(true, "Successfully updated user authorities!");
    }
}
