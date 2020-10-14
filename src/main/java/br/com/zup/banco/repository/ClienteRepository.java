
package br.com.zup.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.banco.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,String>{

    
}