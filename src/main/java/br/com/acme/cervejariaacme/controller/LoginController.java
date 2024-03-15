package br.com.acme.cervejariaacme.controller;

import br.com.acme.cervejariaacme.model.Usuario;
import br.com.acme.cervejariaacme.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UsuarioService usuarioService;
    @GetMapping
    public String showFormLogin(Model model, @ModelAttribute("message") Object message,
                                @ModelAttribute("login-sucesso") Object loginSucesso  ){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("message", message);
        model.addAttribute("loginSucesso", loginSucesso);
        return "login/index";
    }

    @PostMapping("/efetuarLogin")
    public String efetuarLogin(Usuario usuario, Model model, RedirectAttributes redirectAttributes, HttpSession session){
        System.out.println(usuario);
        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(usuario.getEmail());
        if(usuarioOpt.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Usuário não cadastrado");
            return "redirect:/login";
        }else{
            Usuario usuarioBanco = usuarioOpt.get();
            if(usuarioBanco.getPassword().equals(usuario.getPassword())){
                session.setAttribute("usuarioLogado", usuarioBanco);
                return "redirect:/";
            }else {
                redirectAttributes.addFlashAttribute("message", "Senha Inválida");
                redirectAttributes.addFlashAttribute("login-sucesso", false);
                return "redirect:/login";
            }
        }

    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("usuarioLogado", null);
        return  "redirect:/login";
    }
}
