package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE user_id = #{userId}")
    List<UserCredential> getCredentialsByUsername(Long userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, user_id) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    void insertCredentialByUsername(
            String url,
            String username,
            String key,
            String password,
            Long userId);
}