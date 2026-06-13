package org.example.helpdesk.controller;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.enums.Role;
import org.example.helpdesk.service.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser appUser) {
        appUser.setRole(Role.USER);
        return appUserService.create(appUser);
    }
}