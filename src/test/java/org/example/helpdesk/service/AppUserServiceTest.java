package org.example.helpdesk.service;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.enums.Role;
import org.example.helpdesk.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @Test
    void shouldFindAllUsers() {
        AppUser user = new AppUser(1L, "user", "user@test.com", "pass", Role.USER);

        when(appUserRepository.findAll()).thenReturn(List.of(user));

        List<AppUser> result = appUserService.findAll();

        assertEquals(1, result.size());
        assertEquals("user", result.get(0).getUsername());
    }

    @Test
    void shouldFindUserById() {
        AppUser user = new AppUser(1L, "admin", "admin@test.com", "pass", Role.ADMIN);

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));

        AppUser result = appUserService.findById(1L);

        assertEquals("admin", result.getUsername());
        assertEquals(Role.ADMIN, result.getRole());
    }

    @Test
    void shouldCreateUser() {
        AppUser user = new AppUser(null, "user", "user@test.com", "pass", Role.USER);
        AppUser savedUser = new AppUser(1L, "user", "user@test.com", "pass", Role.USER);

        when(appUserRepository.save(user)).thenReturn(savedUser);

        AppUser result = appUserService.create(user);

        assertEquals(1L, result.getId());
        verify(appUserRepository).save(user);
    }

    @Test
    void shouldUpdateUser() {
        AppUser existingUser = new AppUser(1L, "old", "old@test.com", "oldpass", Role.USER);
        AppUser updatedUser = new AppUser(1L, "new", "new@test.com", "newpass", Role.ADMIN);

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(appUserRepository.save(existingUser)).thenReturn(existingUser);

        AppUser result = appUserService.update(1L, updatedUser);

        assertEquals("new", result.getUsername());
        assertEquals("new@test.com", result.getEmail());
        assertEquals("newpass", result.getPassword());
        assertEquals(Role.ADMIN, result.getRole());
    }

    @Test
    void shouldDeleteUser() {
        appUserService.delete(1L);

        verify(appUserRepository).deleteById(1L);
    }
}