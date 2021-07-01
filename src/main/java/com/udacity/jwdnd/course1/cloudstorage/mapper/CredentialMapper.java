package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, key, hashedpassword, userid) VALUES(#{url}, #{userName}, #{key}, #{hashedPassword}, #{userId})")
    void create(String url, String userName, String key, String hashedPassword, Integer userId);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, key=#{key}, hashedpassword=#{hashedPassword} WHERE credentialid=#{credentialId}")
    void save(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    Credential[] list(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    Credential get(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    void delete(Integer credentialId);

    @Select("SELECT EXISTS(SELECT 1 FROM CREDENTIALS WHERE url=#{url} AND username=#{userName})")
    boolean exists(String url, String userName);
}
