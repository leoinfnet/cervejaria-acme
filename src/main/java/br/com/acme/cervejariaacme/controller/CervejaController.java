package br.com.acme.cervejariaacme.controller;

import br.com.acme.cervejariaacme.model.Cerveja;
import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.service.CervejaService;
import br.com.acme.cervejariaacme.service.EstiloService;
import br.com.acme.cervejariaacme.service.LupuloService;
import br.com.acme.cervejariaacme.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/cervejas")
public class CervejaController {
    @Autowired
    CervejaService cervejaService;
    @Autowired
    EstiloService estiloService;
    @Autowired
    MarcaService marcaService;
    @Autowired
    LupuloService lupuloService;
    @GetMapping("/index")
    public String getAll(Model model , @ModelAttribute("sucesso") Object sucesso,
                              @ModelAttribute("sucessoDelete") Object sucessoDelete,
                              @ModelAttribute("message") Object message
    ){
        model.addAttribute("module", "cervejas");
        model.addAttribute("cervejas", cervejaService.findAll());

        model.addAttribute(sucesso);
        model.addAttribute(sucessoDelete);

        return "cervejas/index";
    }
    @GetMapping("/adicionarForm")
    public String showAdicionarForm(Cerveja cerveja, Model model){
        model.addAttribute("module", "cervejas");
        model.addAttribute("cerveja", cerveja);
        model.addAttribute("estilos", estiloService.findAll());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("lupulos", lupuloService.findAll());


        return "cervejas/add";
    }
    @PostMapping("/addCerveja")
    public String adicionarCerveja(Cerveja cerveja, Model model, RedirectAttributes redirectAttributes){
        System.out.println(cerveja);
        cervejaService.save(cerveja);
        redirectAttributes.addFlashAttribute("sucesso", true);
        redirectAttributes.addFlashAttribute("message", "Estilo Salvo com sucesso!");

        return "redirect:index";
    }
    @GetMapping("/remover-cerveja/{id}")
    public RedirectView removerCerveja(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){

        model.addAttribute("module", "cervejas");

        try{
            cervejaService.deleteById(id);
            redirectAttributes.addFlashAttribute("sucesso", true);
            redirectAttributes.addFlashAttribute("message", "Cerveja deletada com sucesso!");
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("sucessoDelete",false);
        }
        return new RedirectView("../index");
    }
    @GetMapping("/editarForm/{id}")
    public String showEditarForm(@PathVariable("id") Long id, Cerveja cerveja, Model model){
        model.addAttribute("module", "cervejas");
        Cerveja found = cervejaService.findById(id).get();
        System.out.println(found);
        model.addAttribute("cerveja", found);
        model.addAttribute("estilos", estiloService.findAll());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("lupulos", lupuloService.findAll());
        return "cervejas/edit";
    }
    @PostMapping("/edit")
    public String editarCerveja(Cerveja cerveja, Model model, RedirectAttributes redirectAttributes){
        cervejaService.save(cerveja);
        redirectAttributes.addFlashAttribute("sucesso", true);
        redirectAttributes.addFlashAttribute("message", "Cerveja Editada com sucesso!");
        return "redirect:index";
    }
}
