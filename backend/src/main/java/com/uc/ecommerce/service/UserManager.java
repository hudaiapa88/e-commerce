package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.model.dto.account.SaveUserRequest;
import com.uc.ecommerce.model.dto.account.UpdateUserRequest;
import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.mapper.UserResponseMapper;
import com.uc.ecommerce.repository.UserRepository;
import com.uc.ecommerce.service.imp.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final AccountEmailManager accountEmailManager;

    @Transactional
    @Override
    public UserResponse save(SaveUserRequest saveUserRequest) {
        User user = new User();
        user.setAddress(saveUserRequest.getAddress());
        user.setFirstName(saveUserRequest.getFirstName());
        user.setLastName(saveUserRequest.getLastName());
        user.setUsername(saveUserRequest.getUsername());
        user.setPassword(saveUserRequest.getPassword());
        user.setPhone(saveUserRequest.getPhone());
        user.setEmail(saveUserRequest.getEmail());
        user=userRepository.save(user);
        accountEmailManager.sendEmailToAdminForNewUser(user);
        return userResponseMapper.entityToDto(user);

    }

    @Transactional
    @Override
    public UserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        User user = findById(id);
        user.setAddress(updateUserRequest.getAddress());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setUsername(updateUserRequest.getUsername());
        user.setPhone(updateUserRequest.getPhone());
        user.setEmail(updateUserRequest.getEmail());
        return userResponseMapper.entityToDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getByUsername(String username) {
        return userResponseMapper.entityToDto(findByUsername(username)) ;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException("Kullanıcı adı bulunamadı"));
    }

    @Override
    public UserResponse getById(Long id) {
        return userResponseMapper.entityToDto(findById(id));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Kullanıcı bulunamadı"));
    }
}
