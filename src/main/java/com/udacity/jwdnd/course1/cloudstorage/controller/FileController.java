package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        if (fileUpload.isEmpty()) {
            return "redirect:/result?isSuccess=" + false + "&errorType=" + 1;
        }

        String fileName = fileUpload.getOriginalFilename();
        if (!fileService.isFileNameAvailableForUser(username, fileName)) {
            return "redirect:/result?isSuccess=" + false + "&errorType=" + 2;
        }

        try {
            fileService.saveFile(fileUpload, username);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/result?isSuccess=" + false;
        }

        return "redirect:/result?isSuccess=" + true;
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam(required = false, name = "fileId") Long fileId) {
        Boolean isSuccess = fileService.deleteFile(fileId);
        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(required = false, name = "fileId") Long fileId) {
        File file = fileService.getFileByFileId(fileId);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(file.getFileData()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(resource);
    }
}
