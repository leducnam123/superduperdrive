package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserCredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final EncryptionService encryptionService;
    private final CredentialMapper credentialMapper;
    private final UserCredentialMapper userCredentialMapper;
    private final UserMapper userMapper;

    public List<UserCredential> getCredentialsByUsername(String username) {
        Long userId = userMapper.getUserIdByUsername(username);
        List<UserCredential> userCredentialList = userCredentialMapper.getCredentialsByUsername(userId);
        return userCredentialList.stream().peek(this::decryptPassword).collect(Collectors.toList());
    }

    public Boolean insertOrUpdateCredential(UserCredential userCredential, String username) {
        Long credentialId = userCredential.getCredentialId();
        String encryptedPassword = encryptPassword(userCredential);
        userCredential.setPassword(encryptedPassword);

        if (null == credentialId) {
            userCredentialMapper.insertCredentialByUsername(
                    userCredential.getUrl(),
                    userCredential.getUsername(),
                    userCredential.getKey(),
                    userCredential.getPassword(),
                    userMapper.getUserIdByUsername(username));
        } else {
            credentialMapper.update(
                    userCredential.getUrl(),
                    userCredential.getUsername(),
                    userCredential.getKey(),
                    userCredential.getPassword(),
                    credentialId);
        }
        return true;
    }

    public Boolean deleteCredential(Long credentialId) {
        credentialMapper.delete(credentialId);
        return true;
    }

    private String encryptPassword(UserCredential userCredential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        userCredential.setKey(encodedKey);
        return encryptionService.encryptValue(userCredential.getPassword(), encodedKey);
    }

    private void decryptPassword(UserCredential userCredential) {
        String decodedPassword = encryptionService.decryptValue(userCredential.getPassword(), userCredential.getKey());
        userCredential.setDecryptedPassword(decodedPassword);
    }
}
