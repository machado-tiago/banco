package br.com.zup.banco.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.banco.model.Cliente;

@Service
public class FileService {
    @Value("${file.upload-location}")
    private String uploadLocation;

    @Autowired
    ClienteService clienteService;

    public void salvar(MultipartFile file, Cliente cliente) throws IllegalStateException, IOException {
            Path locationPath = Paths.get(uploadLocation, file.getOriginalFilename());
            file.transferTo(locationPath);
            cliente.setCpfFile(locationPath.toString());
            clienteService.salvar(cliente);
        }
}
