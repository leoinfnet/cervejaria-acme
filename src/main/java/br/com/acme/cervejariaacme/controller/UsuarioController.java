package br.com.acme.cervejariaacme.controller;

import br.com.acme.cervejariaacme.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @GetMapping
    public String getAll(Model model){
        this.usuarioService.findAll();
        model.addAttribute("usuarios", usuarioService.findAll());
        return "/usuario/index";
    }
}
