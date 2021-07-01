package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.Principal;
import java.util.Map;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {
    private final UserService userService;
    private final CredentialService credentialService;
    private final String module = "credentials";

    public CredentialsController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    // @RequestMapping("get/{id}")
    // }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, Principal principal, final RedirectAttributes redirectAttributes) {
        try {
            credentialService.delete(id);
            redirectAttributes.addFlashAttribute("operationCredentialSuccess", "Credential successfully deleted");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("operationCredentialError", e.getMessage());
        }
        
        redirectAttributes.addFlashAttribute("module", module);
        return "redirect:/home";
    }

    @PostMapping()
    @RequestMapping("put")
    public String put(Model model, Principal principal, @RequestParam Map<String, String> queryMap, final RedirectAttributes redirectAttributes) {
        try {
            Integer userId = userService.get(principal.getName()).getUserId();
            String credentialId = queryMap.get("credentialId");
            String credentialUrl = queryMap.get("url");
            String credentialUserName = queryMap.get("username");
            String credentialPassword = queryMap.get("password");
            credentialService.save(credentialId, credentialUrl, credentialUserName, credentialPassword, userId);
            redirectAttributes.addFlashAttribute("operationCredentialSuccess", "Credential successfully saved");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("operationCredentialError", e.getMessage());
        }
        
        redirectAttributes.addFlashAttribute("module", module);
        return "redirect:/home";
    }
}
