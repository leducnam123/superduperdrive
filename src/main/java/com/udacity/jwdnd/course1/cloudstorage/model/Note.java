package com.udacity.jwdnd.course1.cloudstorage.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Note {

    private Long noteId;
    private String noteTitle;
    private String noteDescription;
    private Long userId;

    public Note(Long noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }
}