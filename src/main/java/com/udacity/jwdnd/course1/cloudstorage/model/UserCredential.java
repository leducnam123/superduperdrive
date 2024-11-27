package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCredential {

    private Long credentialId;
    private Long userId;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decryptedPassword;
}