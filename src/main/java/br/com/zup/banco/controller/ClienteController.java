package br.com.zup.banco.controller;

import java.net.URI;

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
import br.com.zup.banco.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> novo(@Valid @RequestBody Cliente cliente, UriComponentsBuilder uriComponentsBuilder) {
        Object result = clienteService.salvar(cliente);
        if (result.getClass()==(cliente.getClass())) {
            URI uri = uriComponentsBuilder.path("/cliente/{cpf}/endereco").buildAndExpand(cliente.getCpf()).toUri(); 
            return ResponseEntity.created(uri).body(result);

        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
   
    @GetMapping
    @RequestMapping("/{cpf}")
    public ResponseEntity<Object> getCliente(@PathVariable("cpf") String cpf){
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(cliente);
        }
    }

}
