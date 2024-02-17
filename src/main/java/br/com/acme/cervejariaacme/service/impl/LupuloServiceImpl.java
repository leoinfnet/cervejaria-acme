package br.com.acme.cervejariaacme.service.impl;

import br.com.acme.cervejariaacme.model.Lupulo;
import br.com.acme.cervejariaacme.repository.LupuloRepository;
import br.com.acme.cervejariaacme.service.LupuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LupuloServiceImpl implements LupuloService {
    @Autowired
    LupuloRepository lupuloRepository;
    @Override
    public List<Lupulo> findAll() {
        PageRequest pageRequest = PageRequest.ofSize(20);
        return lupuloRepository.findAll(pageRequest).stream().toList();
    }

    @Override
    public List<Lupulo> findAll(int page, int size) {
        //List<Lupulo> all = findAll();
        //PageRequest pageRequest = PageRequest.ofSize(i);
        Sort ascendingByNome = Sort.by("nome").ascending();
        PageRequest pageRequest = PageRequest.of(page, size,ascendingByNome);
        return lupuloRepository.findAll(pageRequest).stream().toList();
    }
    @Override
    public List<Lupulo> findAll(int page, int size, boolean asceding) {
        Sort order = asceding ? Sort.by("nome").ascending() : Sort.by("nome").descending();
        PageRequest pageRequest = PageRequest.of(page, size,order);
        return lupuloRepository.findAll(pageRequest).stream().toList();
    }
    @Override
    public List<Lupulo> findAllByName(String nome) {
      //  List<Lupulo> all = findAll();
        //return all.stream().filter(lupulo -> lupulo.getNome().startsWith(nome)).toList();
        return lupuloRepository.findAllByNomeStartsWith(nome);
    }
    @Override
    public List<Lupulo> findAllByNomeContains(String nome) {
        return lupuloRepository.findAllByNomeContains(nome);
    }

}
