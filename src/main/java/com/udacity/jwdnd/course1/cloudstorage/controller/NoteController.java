package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/note")
    public String noteSubmit(@ModelAttribute("userNote") UserNote userNote, Authentication authentication
    ) {
        String username = authentication.getName();
        Boolean isSuccess = noteService.insertOrUpdateNoteByUser(username, userNote);
        return "redirect:/result?isSuccess=" + isSuccess + "#nav-notes";
    }

    @GetMapping("/note")
    public String noteDeletion(@ModelAttribute("userNote") UserNote userNote, @RequestParam(required = false, name = "noteId") Long noteId) {
        Boolean isSuccess = noteService.deleteNote(noteId);
        return "redirect:/result?isSuccess=" + isSuccess + "#nav-notes";
    }
}
