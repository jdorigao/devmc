package br.com.jdorigao.devmc.controllers;

import br.com.jdorigao.devmc.dto.CategoryDTO;
import br.com.jdorigao.devmc.entities.Category;
import br.com.jdorigao.devmc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Category> find(@PathVariable Integer id) {
        Category obj = categoryService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDTO) {
        Category obj = categoryService.fromDTO(objDTO);
        obj = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDTO, @PathVariable Integer id) {
        Category obj = categoryService.fromDTO(objDTO);
        obj.setId(id);
        obj = categoryService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> list = categoryService.findAll();
        List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> listDTO = list.map(obj -> new CategoryDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
