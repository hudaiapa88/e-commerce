package com.uc.ecommerce.controller.user;

import com.uc.ecommerce.core.security.annotation.OnlyAdmin;
import com.uc.ecommerce.model.dto.account.SaveUserRequest;
import com.uc.ecommerce.model.dto.account.UpdateUserRequest;
import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.service.imp.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse save(@Valid @RequestBody SaveUserRequest saveUserRequest){
          return userService.save(saveUserRequest);
    }
    @PutMapping("/{id}")
    public UserResponse save(@PathVariable Long id,@Valid @RequestBody  UpdateUserRequest updateUserRequest){
        return userService.update(id,updateUserRequest);
    }
    @OnlyAdmin
    @GetMapping("/{id}/active")
    public void activeUser(@PathVariable Long id){
        userService.active(id);
    }

}
