package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final UserService userService;

    public boolean signupUser(UserVO userVO) {
        String username = userVO.getUsername();
        if (!userService.isUsernameAvailable(username)) {
            return false;
        }

        userService.createUser(userVO);
        return true;
    }
}
