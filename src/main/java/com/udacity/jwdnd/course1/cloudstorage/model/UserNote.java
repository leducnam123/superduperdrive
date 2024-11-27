package com.udacity.jwdnd.course1.cloudstorage.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserNote {

    private Long noteId;
    private Long userId;
    private String noteTitle;
    private String noteDescription;

    @Override
    public String toString() {
        return "UserNote{" +
                "userId=" + userId +
                ", noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                '}';
    }
}