package br.com.jdorigao.devmc.controllers;

import br.com.jdorigao.devmc.controllers.utils.URL;
import br.com.jdorigao.devmc.dto.ProductDTO;
import br.com.jdorigao.devmc.entities.Product;
import br.com.jdorigao.devmc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        Product obj = productService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String decodedName = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = productService.search(decodedName, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
