package org.example.helpdesk.service;

import org.example.helpdesk.entity.Category;
import org.example.helpdesk.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

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

    @Test
    void shouldFindAllCategories() {
        Category category = new Category(1L, "Technical", "Technical problems");

        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<Category> result = categoryService.findAll();

        assertEquals(1, result.size());
        assertEquals("Technical", result.get(0).getName());
    }

    @Test
    void shouldUpdateCategory() {
        Category existingCategory = new Category(1L, "Old", "Old description");
        Category updatedCategory = new Category(1L, "New", "New description");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

        Category result = categoryService.update(1L, updatedCategory);

        assertEquals("New", result.getName());
        assertEquals("New description", result.getDescription());
        verify(categoryRepository).save(existingCategory);
    }

    @Test
    void shouldDeleteCategory() {
        categoryService.delete(1L);

        verify(categoryRepository).deleteById(1L);
    }
}