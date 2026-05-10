package com.tnb.possystem.service.impl;

import com.tnb.possystem.domain.UserRole;
import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.mapper.CategoryMapper;
import com.tnb.possystem.modal.Category;
import com.tnb.possystem.modal.Store;
import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.CategoryDto;
import com.tnb.possystem.repository.CategoryRepository;
import com.tnb.possystem.repository.StoreRepository;
import com.tnb.possystem.service.CategoryService;
import com.tnb.possystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        User user = userService.getCurrentUser();

        Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(categoryDto.getName())
                .build();
        checkAuthority(user,category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream()
                .map(
                        CategoryMapper::toDto
                ).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not exist")
        );
        User user = userService.getCurrentUser();
        category.setName(categoryDto.getName());
        checkAuthority(user,category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not exist")
        );
        User user = userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new Exception("You do not have permission to manage this category");
        }
    }
}
