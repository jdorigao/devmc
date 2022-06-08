package br.com.jdorigao.devmc.services;

import br.com.jdorigao.devmc.dto.ClientDTO;
import br.com.jdorigao.devmc.dto.ClientNewDTO;
import br.com.jdorigao.devmc.entities.Address;
import br.com.jdorigao.devmc.entities.City;
import br.com.jdorigao.devmc.entities.Client;
import br.com.jdorigao.devmc.entities.enums.TypeClient;
import br.com.jdorigao.devmc.repositories.AddressRepository;
import br.com.jdorigao.devmc.repositories.ClientRepository;
import br.com.jdorigao.devmc.services.exceptions.DataIntegrityException;
import br.com.jdorigao.devmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        obj = clientRepository.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
    }

    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return clientRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try{
            clientRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Can't delete because there are related orders");
        }
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
    }

    public Client fromDTO(ClientNewDTO objDto) {
        Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), TypeClient.toEnum(objDto.getType()), passwordEncoder.encode(objDto.getPassword()));
        City city = new City(objDto.getCityId(), null, null);
        Address address = new Address(null, objDto.getPublicplace(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getCep(), client, city);
        client.getAddresses().add(address);
        client.getPhones().add(objDto.getPhone1());
        if(objDto.getPhone2() != null){
            client.getPhones().add(objDto.getPhone2());
        }
        if(objDto.getPhone3() != null){
            client.getPhones().add(objDto.getPhone3());
        }
        return client;
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
