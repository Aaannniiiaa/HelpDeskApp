package org.example.helpdesk.config;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.enums.Role;
import org.example.helpdesk.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;

    public DataInitializer(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(String... args) {
        if (appUserRepository.findByUsername("admin").isEmpty()) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setEmail("admin@test.com");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);

            appUserRepository.save(admin);
        }

        if (appUserRepository.findByUsername("user").isEmpty()) {
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setEmail("user@test.com");
            user.setPassword("user123");
            user.setRole(Role.USER);

            appUserRepository.save(user);
        }
    }
}