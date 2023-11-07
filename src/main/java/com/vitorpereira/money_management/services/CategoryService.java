package com.vitorpereira.money_management.services;

import com.vitorpereira.money_management.entities.Category;
import com.vitorpereira.money_management.exceptions.ResourceNotFoundException;
import com.vitorpereira.money_management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category getCategoryById(Integer id){
        Optional<Category> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Category> getCategories(){
        return repository.findAll();
    }
}
