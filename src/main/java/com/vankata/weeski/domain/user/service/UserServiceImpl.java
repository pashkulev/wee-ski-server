package com.vankata.weeski.domain.user.service;

import com.vankata.weeski.domain.role.Authority;
import com.vankata.weeski.domain.role.Role;
import com.vankata.weeski.domain.role.service.RoleService;
import com.vankata.weeski.domain.user.exception.UserNotFoundException;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.domain.user.repository.UserRepository;
import com.vankata.weeski.payload.ApiResponse;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findById(String id) {
        return this.userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public ApiResponse setUserAuthorities(String userId, Authority[] authorities) {
        List<Role> roles = this.roleService.findByAuthorities(authorities);
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }

        User user = optionalUser.get();
        user.setRoles(roles);
        this.userRepository.save(user);

        return new ApiResponse(true, "Successfully updated user authorities!");
    }
}
