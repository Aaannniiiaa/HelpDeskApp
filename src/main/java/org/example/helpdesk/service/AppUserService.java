package org.example.helpdesk.service;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public AppUser create(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser update(Long id, AppUser updatedUser) {
        AppUser existingUser = findById(id);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return appUserRepository.save(existingUser);
    }

    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }
}