package com.vankata.weeski.domain.user.service;

import com.vankata.weeski.domain.user.exception.EmailExistsException;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.domain.user.model.UserRegisterModel;
import com.vankata.weeski.domain.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User register(UserRegisterModel userRegisterModel, MultipartFile imageFile)
            throws EmailExistsException, IOException {

        //todo: try to move this logic to another service or even microservice
        if (imageFile != null) {
            String name = imageFile.getOriginalFilename();
            String imagePath = "C:\\Users\\Vankata\\Desktop\\" + name;
            File targetFile = new File(imagePath);
            Files.copy(imageFile.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            userRegisterModel.setProfilePictureUrl(imagePath);
        }

        Optional<User> optionalUser = this.userRepository.findByEmail(userRegisterModel.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailExistsException();
        }

        User user = this.modelMapper.map(userRegisterModel, User.class);
        this.userRepository.save(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User does not exist!");
        }

        return optionalUser.get();
    }
}
