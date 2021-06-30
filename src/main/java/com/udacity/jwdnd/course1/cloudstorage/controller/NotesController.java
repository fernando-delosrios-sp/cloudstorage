package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.Principal;
import java.util.Map;

import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final UserService userService;
    private final NoteService noteService;
    private final String module = "notes";

    public NotesController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    // @RequestMapping("get/{id}")
    // public @ResponseBody byte[] get(@PathVariable("id") Integer id, Model model, Principal principal, HttpServletResponse response) throws IOException {
    //     Note note = noteService.get(id);
    //     return null;
    // }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, Principal principal, final RedirectAttributes redirectAttributes) {
        noteService.delete(id);
        redirectAttributes.addFlashAttribute("module", module);
        return "redirect:/home";
    }

    @PostMapping()
    @RequestMapping("put")
    public String put(Model model, Principal principal, @RequestParam Map<String, String> queryMap, final RedirectAttributes redirectAttributes) {
        Integer userId = userService.get(principal.getName()).getUserId();
        String noteId = queryMap.get("noteId");
        String noteTitle = queryMap.get("noteTitle");
        String noteDescription = queryMap.get("noteDescription");
        noteService.save(noteId, noteTitle, noteDescription, userId);

        redirectAttributes.addFlashAttribute("module", module);
        return "redirect:/home";
    }
}
