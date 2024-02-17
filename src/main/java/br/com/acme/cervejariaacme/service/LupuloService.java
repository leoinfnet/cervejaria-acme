package br.com.acme.cervejariaacme.service;

import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Lupulo;

import java.util.List;

public interface LupuloService {
    List<Lupulo> findAll();

    List<Lupulo> findAll(int page, int size);
    List<Lupulo> findAll(int page, int size, boolean asceding);
    List<Lupulo> findAllByName(String nome);
    List<Lupulo> findAllByNomeContains(String nome);

}
