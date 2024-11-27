package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<User> getAllUsers();

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM USERS WHERE user_id = #{userId}")
    User getUserById(Long userId);

    @Insert("INSERT INTO USERS(username,salt,password,first_name,last_name) VALUES (" +
            "#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Delete("DELETE FROM USERS WHERE user_id = #{userId}")
    void delete(Long userid);

    @Select("SELECT user_id FROM USERS WHERE username = #{username}")
    Long getUserIdByUsername(@Param("username") String username);
}