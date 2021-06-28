package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final UserService userService;
    private final NoteService noteService;

    public NotesController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @RequestMapping("get/{id}")
    public @ResponseBody byte[] get(@PathVariable("id") Integer id, Model model, Principal principal, HttpServletResponse response) throws IOException {
        Note note = noteService.get(id);
        return null;
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, Principal principal) {
        noteService.delete(id);
        return "redirect:/home";
    }

    @PostMapping()
    @RequestMapping("put")
    public String put(Model model, Principal principal) {
        Integer userId = userService.get(principal.getName()).getUserId();
        Note note = (Note) model.getAttribute("note");
        noteService.save(note, userId);
        return "redirect:/home";
    }
}
