package br.com.zup.banco.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

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
import br.com.zup.banco.service.ClienteService;
import br.com.zup.banco.service.EnderecoService;
import br.com.zup.banco.service.PropostaService;

@RestController
@RequestMapping("/cliente/{cpf}/endereco")
public class EnderecoController {
    
    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    PropostaService propostaService;
    
    @GetMapping
    public ResponseEntity<Object> getEndereco(@PathVariable String cpf){
        Endereco endereco = enderecoService.findByClienteCpf(cpf);
        if (endereco==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(endereco);
        }
    }

    @PostMapping
    public ResponseEntity<Object> novo(@PathVariable String cpf, @RequestBody @Valid Endereco endereco,
    UriComponentsBuilder uriComponentsBuilder) {
        Cliente cliente = clienteService.findByCpf(cpf); 
        if (cliente==null) {
            return ResponseEntity.notFound().build();
        } else if (cliente.getEndereco()==null) {
            endereco = enderecoService.salvar(endereco, cliente);
            URI uri =uriComponentsBuilder.path("/proposta/{id}/cpf_file").buildAndExpand(String.valueOf(propostaService.findLastByClienteCpf(cpf))).toUri();
            return ResponseEntity.created(uri).body(endereco);
        }else{
            Map<String,String> body = new LinkedHashMap<>();
            body.put("error", "O cliente já possui endereço cadastrado");
            return ResponseEntity.badRequest().body(body);
        }
    }
}