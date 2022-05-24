package br.com.jdorigao.devmc.services;

import br.com.jdorigao.devmc.entities.Category;
import br.com.jdorigao.devmc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category find(Integer id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElse(null);
    }
}
