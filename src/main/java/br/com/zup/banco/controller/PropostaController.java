package br.com.zup.banco.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        }else if(proposta.get().getCpfFile()==null){
            Map<String,String> body =  new LinkedHashMap<>();
            body.put("error", "É necessário o upload da foto do CPF para seguir com essa solicitação.");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);

        }else{
            return ResponseEntity.ok().body(proposta.get());
        }
    }
}
