package org.example.helpdesk.controller;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> findAll() {
        return appUserService.findAll();
    }

    @GetMapping("/{id}")
    public AppUser findById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    @PostMapping
    public AppUser create(@RequestBody AppUser appUser) {
        return appUserService.create(appUser);
    }

    @PutMapping("/{id}")
    public AppUser update(@PathVariable Long id, @RequestBody AppUser appUser) {
        return appUserService.update(id, appUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appUserService.delete(id);
    }
}