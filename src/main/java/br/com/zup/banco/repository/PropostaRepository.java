package br.com.zup.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zup.banco.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{
    
    @Query("SELECT MAX(id) FROM Proposta t WHERE t.cliente.cpf =:cpf")
    Long findLastByClienteCpf(@Param ("cpf") String cpf );
}
