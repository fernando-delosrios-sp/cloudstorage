package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    void create(String noteTitle, String noteDescription, Integer userId);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid=#{noteId}")
    void save(Note note);

    @Select("SELECT * FROM NOTES where userid=#{userId}")
    Note[] list(Integer userId);

    @Select("SELECT * FROM NOTES where noteid=#{noteId}")
    Note get(Integer noteId);

    @Delete("DELETE FROM NOTES where noteid=#{noteId}")
    void delete(Integer noteId);
}
