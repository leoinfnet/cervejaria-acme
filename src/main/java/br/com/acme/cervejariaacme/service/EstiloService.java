package br.com.acme.cervejariaacme.service;

import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.model.Marca;

import java.util.List;
import java.util.Optional;

public interface EstiloService {
    void save(Estilo estilo);
    List<Estilo> findAll();
    Optional<Estilo> findById(Long id);
    Estilo update(Long id, Estilo atualizada);
    void deleteById(Long id);
}
