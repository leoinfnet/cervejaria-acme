package br.com.acme.cervejariaacme.repository;

import br.com.acme.cervejariaacme.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstiloRespository extends JpaRepository<Estilo,Long> {
}
