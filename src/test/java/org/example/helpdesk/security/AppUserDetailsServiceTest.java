package org.example.helpdesk.security;

import org.example.helpdesk.entity.AppUser;
import org.example.helpdesk.enums.Role;
import org.example.helpdesk.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @Test
    void shouldLoadUserByUsername() {
        AppUser appUser = new AppUser(1L, "ania", "ania@test.com", "encoded-password", Role.USER);

        when(appUserRepository.findByUsername("ania")).thenReturn(Optional.of(appUser));

        UserDetails result = appUserDetailsService.loadUserByUsername("ania");

        assertEquals("ania", result.getUsername());
        assertEquals("encoded-password", result.getPassword());
        assertTrue(result.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(appUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> appUserDetailsService.loadUserByUsername("unknown"));
    }
}