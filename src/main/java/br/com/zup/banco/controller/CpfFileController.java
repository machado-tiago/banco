package br.com.zup.banco.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.service.ClienteService;
import br.com.zup.banco.service.FileService;

@RestController
@RequestMapping("/cliente/{cpf}/cpf_file")
public class CpfFileController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FileService fileService;


    @PostMapping
    public ResponseEntity<Object> upload(@PathVariable String cpf, @RequestParam MultipartFile file,
            UriComponentsBuilder uriComponentsBuilder) throws IllegalStateException, IOException {
         
        Cliente cliente = clienteService.findByCpf(cpf); 

        if (cliente==null || cliente.getEndereco()==null) {
            return ResponseEntity.notFound().build();

        } else {
            fileService.salvar(file, cliente);
            Map<String,String> body = new LinkedHashMap<>();
            body.put("message", "File successfully uploaded!");
            body.put("fileName", file.getOriginalFilename());
            body.put("file type", file.getContentType());

            URI uri = uriComponentsBuilder.path("/cliente/{cpf}/proposta").buildAndExpand(cliente.getCpf()).toUri(); 
            return ResponseEntity.created(uri).body(body);
        }    
    }
    

}
