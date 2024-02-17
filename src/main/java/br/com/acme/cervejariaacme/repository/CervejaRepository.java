package br.com.acme.cervejariaacme.repository;

import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CervejaRepository extends JpaRepository<Cerveja,Long> {
}
