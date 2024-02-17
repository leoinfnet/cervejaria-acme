package br.com.acme.cervejariaacme;

import br.com.acme.cervejariaacme.model.Lupulo;
import br.com.acme.cervejariaacme.service.LupuloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class LupuloServiceTests {
    @Autowired
    LupuloService lupuloService;
    @Test@DisplayName("Deve testar o findAll")
    public void testaFindAll(){
        int size = lupuloService.findAll().size();
        assertEquals(16, size);
        lupuloService.findAll().forEach(System.out::println);

    }
    @Test@DisplayName("Deve testar a paginacao")
    public void testaPaginacao(){
        List<Lupulo> paginado = lupuloService.findAll(1, 3);
        assertEquals(3, paginado.size());
        Lupulo lupulo1 = paginado.get(0);
        paginado = lupuloService.findAll(2, 3);
        assertEquals(3, paginado.size());
        Lupulo lupulo2 = paginado.get(0);
        assertNotEquals(lupulo1.getId(),lupulo2.getId());
    }
    @Test@DisplayName("Deve testar a busca por nome")
    public void testaBuscaPorNome(){
        List<Lupulo> lupulos = lupuloService.findAllByName("A");
        assertEquals(2, lupulos.size());
        lupulos.forEach(System.out::println);
        lupulos = lupuloService.findAllByName("Aht");
        assertEquals(1, lupulos.size());
        lupulos.forEach(System.out::println);

    }
}
