package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import com.udacity.jwdnd.course1.cloudstorage.model.UserVO;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthorizationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final AuthorizationService authorizationService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final FileService fileService;

    @GetMapping("/home")
    public String getHomepage(@ModelAttribute("userNote") UserNote userNote,
                              @ModelAttribute("userCredential") UserCredential userCredential,
                              Authentication authentication, Model model) {
        String username = (String) authentication.getPrincipal();
        Map<String, Object> data = new HashMap<>();
        data.put("noteList", this.noteService.getNotesByUsername(username));

        if (null == noteService.getNotesByUsername(username)) {
            return "redirect:/login";
        }
        data.put("credentialList", credentialService.getCredentialsByUsername(username));
        data.put("fileList", fileService.getFilesByUser(username));

        model.addAllAttributes(data);
        return "home";
    }

    @GetMapping("/logout")
    public String logOut(@ModelAttribute("userVo") UserVO userVo, Model model) {
        logger.info("logout");
        return this.loginPage(userVo, false, true, model);
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("userVo") UserVO userVo,
                            @RequestParam(required = false, name = "error") Boolean errorValue,
                            @RequestParam(required = false, name = "loggedOut") Boolean loggedOut,
                            Model model) {
        Boolean hasError = errorValue != null && errorValue;
        Boolean isLoggedOut = loggedOut != null && loggedOut;
        Boolean signupSuccessfully = (Boolean) model.getAttribute("signupSuccessfully");

        Map<String, Object> data = new HashMap<>();
        data.put("toLogin", true);
        data.put("loginSuccessfully", false);

        data.put("hasError", hasError);
        data.put("isLoggedOut", isLoggedOut);
        data.put("signupSuccessfully", signupSuccessfully != null && signupSuccessfully);

        model.addAllAttributes(data);
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("userVo") UserVO userVo, Model model) {
        Map<String, Object> data = new HashMap<>();
        data.put("toSignUp", true);
        data.put("signupSuccessfully", false);
        data.put("hasError", false);
        model.addAllAttributes(data);
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute("userVo") UserVO userVo,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        logger.info("Received user info from Signup Form: {}", userVo.toString());
        Map<String, Object> data = new HashMap<>();

        if (!authorizationService.signupUser(userVo)) {
            data.put("toSignUp", true);
            data.put("signupSuccessfully", false);
            data.put("hasError", true);
        } else {
            data.put("toSignUp", false);
            data.put("signupSuccessfully", true);
            data.put("hasError", false);
        }

        redirectAttributes.addFlashAttribute("signupSuccessfully", data.get("signupSuccessfully"));
        model.mergeAttributes(data);
        return "redirect:/login";
    }

    @GetMapping("/result")
    public String showResult(@RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
                             @RequestParam(required = false, name = "errorType") Integer errorType,
                             Model model) {
        Map<String, Object> data = new HashMap<>();
        data.put("isSuccess", isSuccess);
        data.put("errorType", errorType);
        model.addAllAttributes(data);
        return "result";
    }
}
