package br.com.zup.banco.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Cliente result = clienteService.salvar(cliente);
        if (result!=null) {
            URI uri = uriComponentsBuilder.path("/cliente/{cpf}").buildAndExpand(cliente.getCpf()).toUri(); 
            return ResponseEntity.created(uri).body(result);

        } else {
            Map<String,String> map = new LinkedHashMap<>();
            map.put("error", "O cliente deve ter " + Cliente.IDADE_MIN + " anos ou mais.");
            return ResponseEntity.badRequest().body(map);   
        }
    }
}
