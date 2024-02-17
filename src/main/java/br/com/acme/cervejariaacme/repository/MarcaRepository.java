package br.com.acme.cervejariaacme.repository;

import br.com.acme.cervejariaacme.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {
}
