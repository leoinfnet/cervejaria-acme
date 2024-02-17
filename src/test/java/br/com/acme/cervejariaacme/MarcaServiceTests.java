package br.com.acme.cervejariaacme;

import br.com.acme.cervejariaacme.model.Marca;
import br.com.acme.cervejariaacme.service.MarcaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class MarcaServiceTests {
    @Autowired
    MarcaService marcaService;
    @Test@DisplayName("Deve retornar uma lista de Cervejas com 2 elementos")
    public void testaFindAll(){
        List<Marca> marcas = marcaService.findAll();
        assertEquals(2,marcas.size());
        Optional<Marca> paulaner = marcas.stream()
                .filter(marca -> marca.getNome().equals("Paulaner")).findFirst();
        //assertEquals(paulaner.isPresent(), true);
        assertTrue(paulaner.isPresent());
    }
    @Test@DisplayName("Deve testar o find By Id")
    public void testaFindById(){
        Optional<Marca> optDelirium = marcaService.findById(2L);
        assertTrue(optDelirium.isPresent());
        Marca delirium = optDelirium.get();
        assertEquals("Delirium", delirium.getNome());
        assertEquals("Belgica", delirium.getPais());
        assertEquals(2L, delirium.getId());
    }
    @Test@DisplayName("Deve testar as validacoes da classe")
    public void testaValidacoes(){
        LocalDate dataFundacao = LocalDate.of(1902, 6, 15);
        Marca saporro = Marca.builder().pais("Japão").nome("Sapporo").email("sapporocerveja.jp")
                .dataFundacao(dataFundacao).build();
        ConstraintViolationException ex = assertThrows(ConstraintViolationException.class, () -> {
            marcaService.save(saporro);
        });
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Optional<ConstraintViolation<?>> first = constraintViolations.stream().filter(constraintViolation -> constraintViolation.getMessage().equals("O email deve ser um email valido")).findFirst();
        assertTrue(first.isPresent());


    }
    @Test@DisplayName("Deve inserir uma marca no banco")
    public void testaInsert(){
        List<Marca> marcas = marcaService.findAll();
        assertEquals(2,marcas.size());
        LocalDate dataFundacao = LocalDate.of(1902, 6, 15);
        Marca saporro = Marca.builder().pais("Japão").nome("Sapporo").email("sapporo@cerveja.jp")
                .dataFundacao(dataFundacao).build();
        marcaService.save(saporro);
        marcas = marcaService.findAll();
        assertEquals(3,marcas.size());
        Optional<Marca> optSaporro = marcaService.findById(3L);
        assertTrue(optSaporro.isPresent());
        Marca sapporo = optSaporro.get();
        assertEquals(3L, sapporo.getId());
    }
    @Test@DisplayName("Deve testar o delete")
    public void testaDelete(){
        List<Marca> marcas = marcaService.findAll();
        assertEquals(2,marcas.size());
        marcaService.deleteById(1L);
        marcas = marcaService.findAll();
        assertEquals(1,marcas.size());
        Optional<Marca> optPaulaner = marcaService.findById(1L);
        assertTrue(optPaulaner.isEmpty());
    }
}
