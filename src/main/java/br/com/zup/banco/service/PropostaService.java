package br.com.zup.banco.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.banco.model.Cliente;
import br.com.zup.banco.model.Proposta;
import br.com.zup.banco.repository.PropostaRepository;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Transactional
    public Proposta novaProposta(Cliente cliente) {
        return propostaRepository.save(new Proposta (cliente));
    }

    @Transactional
    public Proposta salvar (Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    public Optional<Proposta> findById(Long id) {
        return propostaRepository.findById(id);
    }

	public Long findLastByClienteCpf(String cpf) {
		return propostaRepository.findLastByClienteCpf(cpf);
	}

}
