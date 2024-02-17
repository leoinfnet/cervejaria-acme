package br.com.acme.cervejariaacme.service.impl;

import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.model.Marca;
import br.com.acme.cervejariaacme.repository.EstiloRespository;
import br.com.acme.cervejariaacme.service.EstiloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstiloServiceImpl implements EstiloService {
    @Autowired
    EstiloRespository estiloRespository;
    @Override
    public void save(Estilo estilo) {
        estiloRespository.save(estilo);
    }

    @Override
    public List<Estilo> findAll() {
        return estiloRespository.findAll();
    }

    @Override
    public Optional<Estilo> findById(Long id) {
        return estiloRespository.findById(id);
    }

    @Override
    public Estilo update(Long id, Estilo atualizada) {
        atualizada.setId(id);
        return estiloRespository.save(atualizada);
    }

    @Override
    public void deleteById(Long id) {
        estiloRespository.deleteById(id);

    }
}
