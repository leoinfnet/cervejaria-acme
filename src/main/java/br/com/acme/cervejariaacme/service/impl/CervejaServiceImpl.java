package br.com.acme.cervejariaacme.service.impl;

import br.com.acme.cervejariaacme.criteriaFilters.CervejaFilters;
import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Lupulo;
import br.com.acme.cervejariaacme.repository.CervejaRepository;
import br.com.acme.cervejariaacme.service.CervejaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CervejaServiceImpl implements CervejaService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    CervejaRepository cervejaRepository;
    @Override
    public void save(Cerveja cerveja) {
        cervejaRepository.save(cerveja);
    }

    @Override
    public List<Cerveja> findAll() {
        return cervejaRepository.findAll();
    }

    @Override
    public Optional<Cerveja> findById(Long id) {
        return cervejaRepository.findById(id);
    }
    @Override
    public Cerveja update(Long id, Cerveja atualizada) {
        atualizada.setId(id);
        return cervejaRepository.save(atualizada);
    }
    @Override
    public void deleteById(Long id) {
        cervejaRepository.deleteById(id);
    }

    @Override
    public List<Cerveja> findWithFilters(CervejaFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cerveja> cq = cb.createQuery(Cerveja.class);
        Root<Cerveja> cerveja = cq.from(Cerveja.class);
        /*
        Predicate equal = cb.equal(cerveja.get("nome"), "Founders Kentucky Breakfast");
        Predicate nome = cb.like(cerveja.get("nome"), "Bourbon%");
        cq.where(nome);
         */
         List<Predicate> predicates = new ArrayList<>();

        if(filters.getNome().isPresent() && !filters.getNome().get().isEmpty()){
            String query = filters.getNome().get() + "%";
            Predicate nome = cb.like(cerveja.get("nome"), query);
            predicates.add(nome);
        }
        if(filters.getEstilo().isPresent()){
            Predicate estilo = cb.equal(cerveja.get("estilo"), filters.getEstilo().get());
            predicates.add(estilo);
        }
        if(filters.getMarca().isPresent()){
            Predicate marca = cb.equal(cerveja.get("marca"), filters.getMarca().get());
            predicates.add(marca);
        }
        if(filters.getPais().isPresent() && !filters.getPais().get().isEmpty()){
            Predicate pais = cb.equal(cerveja.get("marca").<String>get("pais"), filters.getPais().get());
            predicates.add(pais);
        }
        if(filters.getLupulos().isPresent()){
            List<Lupulo> lupulos = filters.getLupulos().get();
            List<Long> ids = lupulos.stream().map(Lupulo::getId).toList();
            Predicate ins = cerveja.join("lupulos").get("id").in(ids);
            predicates.add(ins);
        }
        if(false){
            Predicate date = cb.between(cerveja.get("dataFabricacao"), LocalDate.now(), LocalDate.now());
        }
        //Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));
        List<Cerveja> resultList = entityManager.createQuery(cq).getResultList();
        return  resultList;
    }
}
