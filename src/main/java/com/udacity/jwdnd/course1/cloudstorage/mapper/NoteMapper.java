package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    void create(String notetitle, String notedescription, Integer userid);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid=#{noteid}")
    void save(Note note);

    @Select("SELECT * FROM NOTES where userid=#{userid}")
    Note[] list(Integer userid);

    @Select("SELECT * FROM NOTES where noteid=#{noteId}")
    Note get(Integer noteId);

    @Delete("DELETE FROM NOTES where noteid=#{noteId}")
    void delete(Integer noteId);
}
