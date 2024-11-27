package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserFileMapper {
    @Select("SELECT * FROM FILES WHERE user_id = #{userId}")
    List<UserFile> getFileByUserId(Long userId);

    @Select("SELECT * FROM FILES WHERE user_id = #{userId} AND file_name = #{fileName}")
    List<UserFile> getFileByUsernameAndFileName(String fileName, Long userId);
}