package br.com.jdorigao.devmc.controllers;

import br.com.jdorigao.devmc.entities.Category;
import br.com.jdorigao.devmc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Category obj = categoryService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
