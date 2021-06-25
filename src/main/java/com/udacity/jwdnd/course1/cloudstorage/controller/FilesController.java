package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.Principal;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FilesController {
    private final UserService userService;
    private final FileService fileService;

    public FilesController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String getFile() {
        return "home";
    }

    @PostMapping()
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model, Principal principal) {
        Integer userId = userService.getUser(principal.getName()).getUserId();
        fileService.saveFile(file, userId);
        return "home";
    }

    @DeleteMapping()
    public String deleteFile() {
        return "home";
    }
}
