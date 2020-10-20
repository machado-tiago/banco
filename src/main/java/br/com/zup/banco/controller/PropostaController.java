package br.com.zup.banco.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.banco.model.Proposta;
import br.com.zup.banco.service.PropostaService;

@RestController
@RequestMapping("/proposta/{id}")
public class PropostaController {
    
    @Autowired
    PropostaService propostaService;

    @GetMapping
    public ResponseEntity<Object> getCliente(@PathVariable Long id){
        Optional<Proposta> proposta = propostaService.findById(id);
        if (!proposta.isPresent()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(proposta.get());
        }
    }
}
