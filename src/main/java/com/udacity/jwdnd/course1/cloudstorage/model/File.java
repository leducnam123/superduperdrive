package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class File {

    private Long fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Long userId;
    private byte[] fileData;

    public File(
            Long fileId,
            String fileName,
            String contentType,
            String fileSize,
            byte[] fileData) {

        this(fileId, fileName, contentType, fileSize, null, fileData);
    }
}