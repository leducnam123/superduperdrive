package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
@RequiredArgsConstructor
public class CredentialController {

    private final Logger logger = LoggerFactory.getLogger(CredentialController.class);
    private final CredentialService credentialService;

    @PostMapping("/credential")
    public String submitCredential(
            @ModelAttribute("userCredential") UserCredential userCredential,
            Authentication authentication
    ) {
        String username = (String) authentication.getPrincipal();
        Boolean isSuccess = credentialService.insertOrUpdateCredential(userCredential, username);
        return "redirect:/result?isSuccess=" + isSuccess + "#nav-credentials";
    }

    @GetMapping("/credential")
    public String deleteCredential(
            @ModelAttribute("userCredential") UserCredential userCredentialVO,
            @RequestParam(required = false, name = "credentialId") Long credentialId
    ) {
        logger.info("CredentialId: {}", credentialId);
        Boolean isSuccess = credentialService.deleteCredential(credentialId);
        return "redirect:/result?isSuccess=" + isSuccess + "#nav-credentials";
    }
}
