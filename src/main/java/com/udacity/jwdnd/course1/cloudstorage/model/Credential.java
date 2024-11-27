package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Credential {
    private Long credentialId;
    private String url;
    private String key;
    private String username;
    private String password;
    private Integer userId;

    public Credential(
            Long credentialId,
            String url,
            String key,
            String username,
            String password) {

        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
    }
}