package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserNoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteMapper noteMapper;
    private final UserNoteMapper userNoteMapper;
    private final UserMapper userMapper;

    public List<UserNote> getNotesByUsername(String username) {
        Long userId = userMapper.getUserIdByUsername(username);
        if (null == userId) {
            return null;
        }
        return userNoteMapper.getNotesByUserId(userId);
    }

    public Boolean insertOrUpdateNoteByUser(String username, UserNote userNote) {
        String noteTitle = userNote.getNoteTitle();
        String noteDescription = userNote.getNoteDescription();
        Long noteId = userNote.getNoteId();

        if (null == noteId) {
            Long userId = userMapper.getUserIdByUsername(username);
            userNoteMapper.insertNoteByUserId(userId, noteTitle, noteDescription);
        } else {
            this.noteMapper.update(noteTitle, noteDescription, noteId);
        }

        return true;
    }

    public Boolean deleteNote(Long noteId) {
        noteMapper.delete(noteId);
        return true;
    }
}
