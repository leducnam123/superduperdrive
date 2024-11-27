package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserFile {

    private Long fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Long userId;
    private byte[] fileData;

}