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

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, hashedpassword=#{hashedPassword} WHERE credentialid=#{credentialId}")
    void save(Credential credential);

    @Select("SELECT * FROM CREDENTIALS where userid=#{userId}")
    Credential[] list(Integer userId);

    @Select("SELECT * FROM CREDENTIALS where credentialid=#{credentialId}")
    Credential get(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS where credentialid=#{credentialId}")
    void delete(Integer credentialId);
}
