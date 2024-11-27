package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public boolean isUsernameAvailable(String username) {
        return null == userMapper.getUserByUsername(username);
    }

    public int createUser(UserVO userVo) {
        byte[] salt = generateSalt();
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(userVo.getPassword(), encodedSalt);

        User newUser = new User(
                null,
                userVo.getUsername(),
                encodedSalt,
                hashedPassword,
                userVo.getFirstName(),
                userVo.getLastName());

        return userMapper.insert(newUser);
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
