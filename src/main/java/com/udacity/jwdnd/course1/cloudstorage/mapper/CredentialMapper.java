package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userId) VALUES (" +
            "#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credential_id = #{credentialId}")
    Credential getCredentialByCredentialId(Long credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credential_id = #{credentialId}")
    void delete(Long credentialId);

    @Update("UPDATE credentials " +
            "SET url = #{url}, username = #{username}, key = #{key}, " +
            "password = #{password} " +
            "WHERE credential_id = #{credentialId}")
    void update(
            String url, String username, String key, String password, Long credentialId);
}