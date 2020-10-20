package br.com.zup.banco.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.banco.model.Proposta;

@Service
public class FileService {
    @Value("${file.upload-location}")
    private String uploadLocation;

    @Autowired
    PropostaService propostaService;

    @Transactional
    public void salvar(MultipartFile file, Proposta proposta) throws IllegalStateException, IOException {
            Path locationPath = Paths.get(uploadLocation, file.getOriginalFilename());
            file.transferTo(locationPath);
            proposta.setCpfFile(locationPath.toString());
            propostaService.salvar(proposta);
        }
}
