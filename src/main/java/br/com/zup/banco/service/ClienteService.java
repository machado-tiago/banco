package br.com.zup.banco.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public Object salvar(Cliente cliente) {
        Object errorValidation = errorValidation(cliente);
        if (errorValidation==null) {
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

	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}
}
