package com.uc.ecommerce.service.imp;

import com.uc.ecommerce.model.dto.account.CreateUserRequest;
import com.uc.ecommerce.model.dto.account.UpdateUserRequest;
import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.entity.account.User;

import java.util.List;

public interface UserService {
    UserResponse save(CreateUserRequest createUserRequest);
    UserResponse update(Long id, UpdateUserRequest updateUserRequest);
    void delete(Long id);
    UserResponse getByUsername(String username);
    User findByUsername(String username);
    UserResponse getById(Long id);
    User findById(Long id);

    void active(Long id);

    List<UserResponse> getAll();
}
