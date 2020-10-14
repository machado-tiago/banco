package br.com.zup.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    

}
