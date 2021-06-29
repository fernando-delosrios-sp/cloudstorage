package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.Principal;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;
    private final UserService userService;
    private final NoteService noteService;
    private final String defaultModule = "files";

    public HomeController(FileService fileService, UserService userService, NoteService noteService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping()
    public String get(Model model, Principal principal) {
        if (model.getAttribute("module") == null) model.addAttribute("module", defaultModule);
        Integer userId = userService.get(principal.getName()).getUserId();
        File[] files = fileService.list(userId);
        Note[] notes = noteService.list(userId);
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        return "home";
    }
}
