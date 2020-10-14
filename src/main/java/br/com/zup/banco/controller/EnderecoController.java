package br.com.zup.banco.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
    
    @PostMapping
    public ResponseEntity novo(@PathVariable("id") String id, @RequestBody @Valid Endereco endereco) {
        
        return ResponseEntity.ok().body(endereco);
    }
}
