package br.com.acme.cervejariaacme.repository;

import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Lupulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LupuloRepository extends JpaRepository<Lupulo,Long> {
    List<Lupulo> findAllByNomeStartsWith(String nome);
    List<Lupulo> findAllByNomeContains(String nome);
}
