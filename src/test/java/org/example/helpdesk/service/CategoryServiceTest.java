package org.example.helpdesk.service;

import org.example.helpdesk.entity.Category;
import org.example.helpdesk.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldFindCategoryById() {
        Category category = new Category(1L, "Technical problem", "Technical issues");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.findById(1L);

        assertEquals("Technical problem", result.getName());
    }

    @Test
    void shouldCreateCategory() {
        Category category = new Category(null, "Account", "Account problems");
        Category savedCategory = new Category(1L, "Account", "Account problems");

        when(categoryRepository.save(category)).thenReturn(savedCategory);

        Category result = categoryService.create(category);

        assertEquals(1L, result.getId());
        assertEquals("Account", result.getName());
        verify(categoryRepository).save(category);
    }
}