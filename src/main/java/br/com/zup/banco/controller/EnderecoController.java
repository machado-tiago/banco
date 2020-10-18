package br.com.zup.banco.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.model.Endereco;
import br.com.zup.banco.repository.ClienteRepository;
import br.com.zup.banco.service.ClienteService;
import br.com.zup.banco.service.EnderecoService;

@RestController
@RequestMapping("/cliente/{cpf}/endereco")
public class EnderecoController {
    
    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<Object> getEndereco(@PathVariable("cpf") String cpf){
        return ResponseEntity.ok().body(enderecoService.findByClienteCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<Object> novo(@PathVariable("cpf") String cpf, @RequestBody @Valid Endereco endereco,UriComponentsBuilder uriComponentsBuilder) {
        endereco = enderecoService.salvar(endereco, cpf);
        
        URI uri = uriComponentsBuilder.path("/cliente/{cpf}/endereco").buildAndExpand(endereco).toUri();
        return ResponseEntity.ok().body(endereco); 
    }
}