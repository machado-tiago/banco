package br.com.zup.banco.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.model.Endereco;
import br.com.zup.banco.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ClienteService clienteService;

    @Transactional
    public Endereco salvar(Endereco endereco, String cpf){
        Cliente cliente = clienteService.findByCpf(cpf); 
        endereco.setCliente(cliente);
        endereco = enderecoRepository.save(endereco);
        cliente.setEndereco(endereco);
        clienteService.salvar(cliente);
        return endereco;
    }

    @Transactional
	public Endereco findByClienteCpf(String cpf) {
		return enderecoRepository.findByClienteCpf(cpf);
	}

}
