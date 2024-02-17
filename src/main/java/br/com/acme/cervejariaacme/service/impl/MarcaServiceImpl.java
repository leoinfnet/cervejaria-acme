package br.com.acme.cervejariaacme.service.impl;

import br.com.acme.cervejariaacme.model.Marca;
import br.com.acme.cervejariaacme.repository.MarcaRepository;
import br.com.acme.cervejariaacme.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {
    @Autowired
    MarcaRepository marcaRepository;
    @Override
    public void save(Marca marca) {
        marcaRepository.save(marca);
    }

    @Override
    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    @Override
    public Optional<Marca> findById(Long id) {
        return marcaRepository.findById(id);
    }

    @Override
    public Marca update(Long id, Marca atualizada) {
        atualizada.setId(id);
        return marcaRepository.save(atualizada);

    }

    @Override
    public void deleteById(Long id) {
        marcaRepository.deleteById(id);
    }
}
