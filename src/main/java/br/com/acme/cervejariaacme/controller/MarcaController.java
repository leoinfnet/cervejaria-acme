package br.com.acme.cervejariaacme.controller;

import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.model.Marca;
import br.com.acme.cervejariaacme.service.EstiloService;
import br.com.acme.cervejariaacme.service.MarcaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

@Controller
@RequestMapping("/marcas")
public class MarcaController {
    @Autowired
    MarcaService marcaService;
    @Autowired
    EstiloService estiloService;
    @GetMapping("/index")
    public String getAll(Model model , @ModelAttribute("sucesso") Object sucesso,
                         @ModelAttribute("sucessoDelete") Object sucessoDelete,
                         @ModelAttribute("message") Object message
    ){
        model.addAttribute("module", "marcas");
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute(sucesso);
        model.addAttribute(sucessoDelete);
        return "marcas/index";
    }
    @GetMapping("/remover/{id}")
    public RedirectView removerMarca(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){

        model.addAttribute("module", "marcas");
        try{
            marcaService.deleteById(id);
            redirectAttributes.addFlashAttribute("sucessoDelete",true);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("sucessoDelete",false);
        }
        return new RedirectView("../index");
    }

    @GetMapping("/adicionarForm")
    public String showAdicionarForm(Marca marca, Model model){
        model.addAttribute("module", "marcas");
        model.addAttribute("marca", marca);

        return "marcas/add";
    }
    @PostMapping("/add")
    public String adicionarTipoDeCerveja(@Valid  Marca marca, BindingResult results){
        if(results.hasErrors()){
            return "marcas/add" ;
        }
        try{
            marcaService.save(marca);
        }catch (ConstraintViolationException ex){
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            System.out.println(constraintViolations);

        }

        return "redirect:index";
    }

}
