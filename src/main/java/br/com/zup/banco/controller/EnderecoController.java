package br.com.zup.banco.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.banco.model.Endereco;

@RestController
@RequestMapping("/cliente/{id}/endereco")
public class EnderecoController {
    
    //@Consumes (MediaType.APPLICATION_JSON)
    //@Produces (MediaType.APPLICATION_JSON)
    @PostMapping
    public ResponseEntity<Object> novo(@PathVariable("id") String id, @RequestBody @Valid Endereco endereco) {
        //pesquisar o cliente, adicionar o endereço e salvar
        return ResponseEntity.ok().body(endereco); //Response.status(201).build();
    }
}
