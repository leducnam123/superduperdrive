package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserFileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final UserFileMapper userFileMapper;
    private final UserMapper userMapper;

    public Boolean isFileNameAvailableForUser(String username, String filename) {
        Map<String, Object> paramMap = new HashMap<>();
        Long userId = userMapper.getUserIdByUsername(username);
        paramMap.put("userId", userId);
        paramMap.put("filename", filename);
        return userFileMapper.getFileByUsernameAndFileName(filename, userId).isEmpty();
    }

    public List<UserFile> getFilesByUser(String username) {
        Long userId = userMapper.getUserIdByUsername(username);
        return userFileMapper.getFileByUserId(userId);
    }

    public Boolean saveFile(MultipartFile file, String username) throws IOException {
        User user = userMapper.getUserByUsername(username);
        byte[] fileData = file.getBytes();
        String contentType = file.getContentType();

        String fileSize = String.valueOf(file.getSize());
        String fileName = file.getOriginalFilename();
        fileMapper.insert(new File(null, fileName, contentType, fileSize, user.getUserId(), fileData));

        return true;
    }

    public Boolean deleteFile(Long fileId) {
        fileMapper.delete(fileId);
        return true;
    }

    public File getFileByFileId(Long fileId) {
        return this.fileMapper.getFileById(fileId);
    }
}
