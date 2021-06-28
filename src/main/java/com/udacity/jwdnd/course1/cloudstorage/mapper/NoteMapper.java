package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int save(Note note);

    @Select("SELECT * FROM NOTES where userid=#{userId}")
    Note[] list(Integer userId);

    @Select("SELECT * FROM FILES where noteid=#{noteId}")
    Note get(Integer noteId);

    @Delete("DELETE FROM NOTES where noteid=#{noteId}")
    void delete(Integer noteId);
}
