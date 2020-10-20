package br.com.zup.banco.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PropostaService propostaService;

    @Transactional
    public Object novo(Cliente cliente) {
        Object errorValidation = errorValidation(cliente);
        if (errorValidation==null) {
            cliente = clienteRepository.save(cliente);  
            propostaService.novaProposta(cliente);
            return clienteRepository.save(cliente);  
        } else {
            return errorValidation;
        }
    }    

    
    private Map<String,String> errorValidation(Cliente cliente) {
        boolean validated = true;
        Map<String,String> map = new LinkedHashMap<>();
        if (clienteRepository.findByEmail(cliente.getEmail())!=null){
            map.put("email", "O e-mail informado já consta na base de dados.");
            validated=false;
        }

        if (clienteRepository.findByCpf(cliente.getCpf())!=null) {
            map.put("cpf", "O CPF informado já consta na base de dados.");
            validated=false;
        }
        
        if (!cliente.nascimentoCheck()) {
            map.put("nascimento", "O cliente deve ter " + Cliente.IDADE_MIN + " anos ou mais.");
            validated=false;
        }

        if (validated){
            return null;
        }else{
            return map;
        }
        
    }

    @Transactional
	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}


	public Cliente salvar(Cliente cliente) {
            return clienteRepository.save(cliente); 
	}
}
