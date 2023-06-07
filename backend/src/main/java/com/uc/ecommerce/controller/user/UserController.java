package com.uc.ecommerce.controller.user;

import com.uc.ecommerce.model.dto.account.SaveUserRequest;
import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.service.imp.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse save(SaveUserRequest saveUserRequest){

    }
}
