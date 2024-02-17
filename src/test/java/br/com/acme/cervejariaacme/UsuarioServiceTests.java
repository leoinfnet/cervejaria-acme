package br.com.acme.cervejariaacme;

import br.com.acme.cervejariaacme.model.Role;
import br.com.acme.cervejariaacme.model.Usuario;
import br.com.acme.cervejariaacme.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsuarioServiceTests {
    @Autowired
    UsuarioService usuarioService;
    @Test@DisplayName("Deve testar o Find All Ativos")
    public void testaFindAtivos(){
        List<Usuario> ativos = usuarioService.findAllUsuariosAtivos();
        List<Usuario> all = usuarioService.findAll();
        assertEquals(1, ativos.size());
        assertEquals(2,all.size());

    }
    @Test@DisplayName("Deve buscar pelas Roles")
    public void testaFindAllByRoles(){
        Role role = Role.builder().id(1L).build();
        List<Role> roles = List.of(role);
        List<Usuario> allByRoles = usuarioService.findAllByRoles(roles);
        allByRoles.forEach(System.out::println);
    }

}
