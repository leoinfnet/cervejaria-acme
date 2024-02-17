package br.com.acme.cervejariaacme.service;

import br.com.acme.cervejariaacme.criteriaFilters.CervejaFilters;
import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Estilo;

import java.util.List;
import java.util.Optional;

public interface CervejaService {
    void save(Cerveja cerveja);
    List<Cerveja> findAll();
    Optional<Cerveja> findById(Long id);
    Cerveja update(Long id, Cerveja atualizada);
    void deleteById(Long id);

    List<Cerveja> findWithFilters(CervejaFilters filters);
}
