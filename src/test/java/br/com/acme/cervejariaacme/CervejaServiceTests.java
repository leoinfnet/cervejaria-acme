package br.com.acme.cervejariaacme;

import br.com.acme.cervejariaacme.criteriaFilters.CervejaFilters;
import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.model.Lupulo;
import br.com.acme.cervejariaacme.model.Marca;
import br.com.acme.cervejariaacme.service.CervejaService;
import br.com.acme.cervejariaacme.service.EstiloService;
import br.com.acme.cervejariaacme.service.MarcaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
public class CervejaServiceTests {
    @Autowired
    CervejaService cervejaService;
    @Autowired
    EstiloService estiloService;
    @Autowired
    MarcaService marcaService;
    @Test@DisplayName("Deve inserir uma cerveja No Banco")
    public void testaCreate(){
        Estilo estilo = estiloService.findById(3L).get();
        Marca marca = marcaService.findById(2L).get();
        Estilo estiloSurpresa = Estilo.builder().id(3L).build();
        List<Cerveja> all = cervejaService.findAll();
        assertEquals(3, all.size());
        Cerveja wallsPetroleoum = Cerveja.builder().nome("Walls Petroleoum").marca(marca).estilo(estiloSurpresa).build();
        cervejaService.save(wallsPetroleoum);
        all = cervejaService.findAll();
        assertEquals(4, all.size());

        all.forEach(System.out::println);

    }

    @Test@DisplayName("Deve testar a api de Criteria")
    public void testaApiDeCriteria(){
        Estilo pilsner = Estilo.builder().id(1L).build();
        Estilo porter = Estilo.builder().id(3L).build();

        Lupulo lupulo1 = Lupulo.builder().id(3L).build();
        Lupulo lupulo2 = Lupulo.builder().id(5L).build();

        List<Lupulo> lupulos = List.of(lupulo1, lupulo2);
        CervejaFilters bourbon = CervejaFilters.builder().nome(Optional.of("Bourbon"))
                .marca(Optional.empty()).pais(Optional.empty()).lupulos(Optional.empty())
                .estilo(Optional.empty()).build();

        CervejaFilters pislnerFilter = CervejaFilters.builder().nome(Optional.empty())
                .marca(Optional.empty()).pais(Optional.empty()).lupulos(Optional.empty())
                .estilo(Optional.of(pilsner)).build();

        CervejaFilters alemanhaFilter = CervejaFilters.builder().nome(Optional.empty())
                .marca(Optional.empty()).pais(Optional.of("Alemanha")).lupulos(Optional.empty())
                .estilo(Optional.of(porter)).build();

        CervejaFilters lupulosFilter = CervejaFilters.builder().nome(Optional.empty())
                .marca(Optional.empty()).pais(Optional.empty()).lupulos(Optional.of(lupulos))
                .estilo(Optional.empty()).build();

        List<Cerveja> withFilters = cervejaService.findWithFilters(lupulosFilter);
        assertEquals(1,withFilters.size());
    }

}
