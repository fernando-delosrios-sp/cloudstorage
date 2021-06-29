package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int save(File file);

    @Select("SELECT * FROM FILES where userid=#{userId}")
    File[] list(Integer userId);

    @Select("SELECT * FROM FILES where fileid=#{fileId}")
    File get(Integer fileId);

    @Delete("DELETE FROM FILES where fileid=#{fileId}")
    void delete(Integer fileId);

    @Select("SELECT EXISTS(SELECT 1 FROM FILES where filename=#{fileName})")
    Boolean exists(String fileName);
}
