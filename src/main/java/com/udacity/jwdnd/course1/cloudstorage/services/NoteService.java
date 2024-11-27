package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserNoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteMapper noteMapper;
    private final UserNoteMapper userNoteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserNoteMapper userNoteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userNoteMapper = userNoteMapper;
        this.userMapper = userMapper;
    }

    public List<UserNote> getNotesByUsername(String username) {
        Long userId = userMapper.getUserIdByUsername(username);
        if (userId == null) {
            return null;
        }
        return this.userNoteMapper.getNotesByUserId(userId);
    }

    public Boolean insertOrUpdateNoteByUser(String username, UserNote userNote) {
        String noteTitle = userNote.getNoteTitle();
        String noteDescription = userNote.getNoteDescription();
        Long noteId = userNote.getNoteId();

        if (null == noteId) {
            Long userId = userMapper.getUserIdByUsername(username);
            this.userNoteMapper.insertNoteByUserId(userId, noteTitle, noteDescription);
        } else {
            this.noteMapper.update(noteTitle, noteDescription, noteId);
        }
        return true;
    }

    public Boolean deleteNote(Long noteId) {
        this.noteMapper.delete(noteId);
        return true;
    }
}