package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES(" +
            "file_name, " +
            "content_type, " +
            "file_size, " +
            "file_data, " +
            "user_id) VALUES (" +
            "#{fileName}, " +
            "#{contentType}, " +
            "#{fileSize}, " +
            "#{fileData}, " +
            "#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);
    @Select("SELECT * FROM FILES WHERE file_id = #{fileId}")
    File getFileById(Long fileId);

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Delete("DELETE FROM FILES WHERE file_id = #{fileId}")
    void delete(Long fileId);
}