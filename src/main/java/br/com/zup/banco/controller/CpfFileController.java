package br.com.zup.banco.controller;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.banco.model.Proposta;
import br.com.zup.banco.service.FileService;
import br.com.zup.banco.service.PropostaService;

@RestController
@RequestMapping("/proposta/{id}/cpf_file")
public class CpfFileController {

    @Autowired
    private PropostaService propostaService;

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<Object> upload(@PathVariable("id") Long id, @RequestParam MultipartFile file,
            UriComponentsBuilder uriComponentsBuilder) throws IllegalStateException, IOException {

        if (file.isEmpty()) {
            Map<String,String> body =  new LinkedHashMap<>();
            body.put("field", "file");
            body.put("error", "O campo é obrigatório.");
            return ResponseEntity.badRequest().body(body);
        } else {
            Optional<Proposta> proposta = propostaService.findById(id);

            if (!proposta.isPresent()) {
                return ResponseEntity.notFound().build();

            }else if(proposta.get().getCliente().getEndereco()==null){
                Map<String,String> body =  new LinkedHashMap<>();
                body.put("error", "É necessário que o endereço esteja preenchido para seguir com essa solicitação.");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);

            } else {
                fileService.salvar(file, proposta.get());
                Map<String,String> body = new LinkedHashMap<>();
                body.put("message", "File successfully uploaded!");
                body.put("fileName", file.getOriginalFilename());
                body.put("file type", file.getContentType());
    
                URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(proposta.get().getId()).toUri(); 
                return ResponseEntity.created(uri).body(body);
            }    
        }
        
    }
    

}
