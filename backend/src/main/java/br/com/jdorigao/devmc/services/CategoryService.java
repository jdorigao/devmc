package br.com.jdorigao.devmc.services;

import br.com.jdorigao.devmc.entities.Category;
import br.com.jdorigao.devmc.repositories.CategoryRepository;
import br.com.jdorigao.devmc.services.exceptions.DataIntegrityException;
import br.com.jdorigao.devmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category find(Integer id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return categoryRepository.save(obj);
    }

    public Category update(Category obj) {
        find(obj.getId());
        return categoryRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try{
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Can't delete a category that has product ");
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
    }
}
