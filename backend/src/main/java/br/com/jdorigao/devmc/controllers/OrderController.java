package br.com.jdorigao.devmc.controllers;

import br.com.jdorigao.devmc.entities.Order;
import br.com.jdorigao.devmc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Order> find(@PathVariable Integer id) {
        Order obj = orderService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
