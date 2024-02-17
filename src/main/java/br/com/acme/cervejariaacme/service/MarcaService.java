package br.com.acme.cervejariaacme.service;

import br.com.acme.cervejariaacme.model.Marca;

import java.util.List;
import java.util.Optional;

public interface MarcaService {
    void save(Marca marca);
    List<Marca> findAll();
    Optional<Marca> findById(Long id);
    Marca update(Long id, Marca atualizada);
    void deleteById(Long id);

}
