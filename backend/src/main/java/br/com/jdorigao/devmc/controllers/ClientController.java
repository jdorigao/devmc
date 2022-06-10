package br.com.jdorigao.devmc.controllers;

import br.com.jdorigao.devmc.dto.ClientDTO;
import br.com.jdorigao.devmc.dto.ClientNewDTO;
import br.com.jdorigao.devmc.entities.Client;
import br.com.jdorigao.devmc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable Integer id) {
        Client obj = clientService.fromDTO(objDTO);
        obj.setId(id);
        obj = clientService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> list = clientService.findAll();
        List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Client> list = clientService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> listDTO = list.map(obj -> new ClientDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Client> insert(@Valid @RequestBody ClientNewDTO objDTO){
        Client obj = clientService.fromDTO(objDTO);
        obj = clientService.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
