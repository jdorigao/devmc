package br.com.jdorigao.devmc.controllers;

import br.com.jdorigao.devmc.entities.Client;
import br.com.jdorigao.devmc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Client> find(@PathVariable Integer id) {
        Client obj = clientService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
