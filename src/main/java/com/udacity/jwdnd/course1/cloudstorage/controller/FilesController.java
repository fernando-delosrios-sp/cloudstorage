package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
public class FilesController {
    private final UserService userService;
    private final FileService fileService;
    private final String module = "files";

    public FilesController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @RequestMapping("get/{id}")
    public @ResponseBody byte[] get(@PathVariable("id") Integer id, Model model, Principal principal, HttpServletResponse response) throws IOException {
        File file = fileService.get(id);
        response.setContentType(file.getContenttype());
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
        return file.getFiledata();
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, Principal principal) {
        fileService.delete(id);
        return "redirect:/home";
    }

    @PostMapping()
    @RequestMapping("put")
    public String put(@RequestParam("fileUpload") MultipartFile file, Model model, Principal principal, final RedirectAttributes redirectAttributes) {
        Integer userId = userService.get(principal.getName()).getUserId();
        fileService.save(file, userId);
        redirectAttributes.addFlashAttribute("module", module);
        return "redirect:/home";
    }
}
