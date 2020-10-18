package br.com.zup.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.banco.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{

	Endereco findByCliente(String cpf);

	Endereco findByClienteCpf(String cpf);

}
