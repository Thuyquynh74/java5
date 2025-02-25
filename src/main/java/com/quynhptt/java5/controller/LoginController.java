package com.quynhptt.java5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @RequestMapping("/loginSuccess")
    public String loginSuccess(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/products";  // Redirect to the admin page if the user has the ADMIN role
        } else {
            return "redirect:/";  // Redirect to the home page for other users
        }
    }
}
