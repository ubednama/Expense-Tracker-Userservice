package com.example.userService.service;

import com.example.userService.entities.UserInfo;
import com.example.userService.entities.UserInfoDTO;
import com.example.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserInfoDTO createOrUpdateUser(UserInfoDTO userInfoDTO) {
        UnaryOperator<UserInfo> updatingUser = user -> {
            return userRepository.save(userInfoDTO.transformToUserInfo());
        };

        Supplier<UserInfo> createUser = () -> {
            return userRepository.save(userInfoDTO.transformToUserInfo());
        };

        UserInfo userInfo = userRepository.findByUserId(userInfoDTO.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);
        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }

    public UserInfoDTO getUser(UserInfoDTO userInfoDTO) throws Exception{
        Optional<UserInfo> userInfoDTOOpt = userRepository.findByUserId(userInfoDTO.getUserId());

        if(userInfoDTOOpt.isEmpty()) {
            throw new Exception("User not found");
        }
        UserInfo userInfo = userInfoDTOOpt.get();
        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }
}
