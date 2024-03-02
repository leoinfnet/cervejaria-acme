package br.com.acme.cervejariaacme.controller;

import br.com.acme.cervejariaacme.criteriaFilters.CervejaFilters;
import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.service.CervejaService;
import br.com.acme.cervejariaacme.service.EstiloService;
import br.com.acme.cervejariaacme.service.LupuloService;
import br.com.acme.cervejariaacme.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/relatorio-cervejas")
@Transactional
public class RelatorioCervejasController {
    @Autowired
    MarcaService marcaService;
    @Autowired
    EstiloService estiloService;
    @Autowired
    LupuloService lupuloService;
    @Autowired
    CervejaService cervejaService;
    @GetMapping("/index")
    public String index(Model model,RedirectAttributes redirectAttributes){
        List<Cerveja> cervejas = (List<Cerveja>) redirectAttributes.getAttribute("cervejas");
        System.out.println(cervejas);
        model.addAttribute("module", "relatorio-cervejas");
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("estilos", estiloService.findAll());
        model.addAttribute("lupulos", lupuloService.findAll());
        model.addAttribute("cerveja", new CervejaFilters());
        model.addAttribute("cervejas", model.getAttribute("cervejas"));
        return "relatorioCervejas/index";
    }
    @PostMapping("/find")
    public String find(CervejaFilters cervejaFilters, RedirectAttributes redirectAttributes){
        System.out.println(cervejaFilters);
        List<Cerveja> withFilters = cervejaService.findWithFilters(cervejaFilters);
        redirectAttributes.addFlashAttribute("cervejas", withFilters);
        return "redirect:index";
    }
}
