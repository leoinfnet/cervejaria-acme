package br.com.acme.cervejariaacme.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping
    public String dashboard(Model model, HttpSession session){

        List<String> vendasPorDias = List.of("20", "10", "40", "100", "200", "400", "22");
        model.addAttribute("module", "dashboard");
        model.addAttribute("vendasPorDias", vendasPorDias);

        return "index";
    }
}
