package br.com.fabricadeprogramador.ws.repository;

import br.com.fabricadeprogramador.ws.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
