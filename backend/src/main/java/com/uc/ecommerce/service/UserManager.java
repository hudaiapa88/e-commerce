package com.uc.ecommerce.service;

import com.uc.ecommerce.core.exception.EntityNotFoundException;
import com.uc.ecommerce.core.generator.CodeGenerator;
import com.uc.ecommerce.core.i18n.Translator;
import com.uc.ecommerce.model.dto.account.CreateUserRequest;
import com.uc.ecommerce.model.dto.account.UpdateUserRequest;
import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.enums.AccountLogType;
import com.uc.ecommerce.model.mapper.UserResponseMapper;
import com.uc.ecommerce.repository.UserRepository;
import com.uc.ecommerce.service.abstracts.AccountLogService;
import com.uc.ecommerce.service.abstracts.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final AccountEmailManager accountEmailManager;
    private final CodeGenerator codeGenerator;
    private final BCryptPasswordEncoder encoder;
    private final AccountLogService accountLogService;

    @Transactional
    @Override
    public UserResponse save(CreateUserRequest createUserRequest) {
       User user=userRepository.save(User.create(createUserRequest,encoder.encode(createUserRequest.getPassword()),codeGenerator.generate()));
        accountEmailManager.sendEmailToAdminForNewUser(user);
        return userResponseMapper.entityToDto(user);

    }

    @Transactional
    @Override
    public UserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        User user = findById(id);
        return userResponseMapper.entityToDto(userRepository.save(user.update(updateUserRequest)));
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
        return userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException(Translator.toLocale("user.EntityNotFoundException")));
    }

    @Override
    public UserResponse getById(Long id) {
        return userResponseMapper.entityToDto(findById(id));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Translator.toLocale("user.id.EntityNotFoundException")));
    }
    @Transactional
    @Override
    public void active(Long id) {
        User user= findById(id);
        accountLogService.deleteByAccount_IdAndAccountLogType(id, AccountLogType.WRONG_ENTRY);
        userRepository.save(user.active());
    }

    @Override
    public List<UserResponse> getAll() {
        return userResponseMapper.entityListToDtoList(userRepository.findAll());
    }
}
