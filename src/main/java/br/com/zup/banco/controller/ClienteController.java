package br.com.zup.banco.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.banco.model.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @PostMapping
    public ResponseEntity<Object> novo(@Valid @RequestBody Cliente cliente, MethodArgumentNotValidException ex,UriComponentsBuilder uriComponentsBuilder) {
        if (ex.equals(null)){
            URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getCpf()).toUri(); 
            return ResponseEntity.created(uri).body(cliente); //retorna o código 201, respondendo o objeto criado no corpo da resposta
        }else{
            //Get all errors
            List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors.toArray());
        }
    }
}
