package br.com.jdorigao.devmc.services;

import br.com.jdorigao.devmc.entities.Client;
import br.com.jdorigao.devmc.repositories.ClientRepository;
import br.com.jdorigao.devmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }
}
