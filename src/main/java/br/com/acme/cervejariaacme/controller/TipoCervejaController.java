package br.com.acme.cervejariaacme.controller;

import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.service.EstiloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/tipo-cervejas")
public class TipoCervejaController {
    @Autowired
    EstiloService estiloService;
    @GetMapping("/index")
    public String getAllTipos(Model model , @ModelAttribute("sucesso") Object sucesso,
                              @ModelAttribute("sucessoDelete") Object sucessoDelete,
                              @ModelAttribute("message") Object message
                              ){
        model.addAttribute("module", "tipoCerveja");
        model.addAttribute("tiposCervejas", estiloService.findAll());
        model.addAttribute(sucesso);
        model.addAttribute(sucessoDelete);

        return "tipoCervejas/index";
    }
    @GetMapping("/adicionarForm")
    public String showAdicionarForm(Estilo tipoCerveja, Model model){
        model.addAttribute("module", "tipoCerveja");
        model.addAttribute("tipoCerveja", tipoCerveja);

        return "tipoCervejas/add";
    }
    @GetMapping("/editarForm/{id}")
    public String showEditarForm(@PathVariable("id") Long id, Estilo tipoCerveja, Model model){
        model.addAttribute("module", "tipoCerveja");
        Estilo estilo = estiloService.findById(id).get();
        model.addAttribute("tipoCerveja", estilo);
        return "tipoCervejas/edit";
    }
    @PostMapping("/editTipoCerveja")
    public String editarTipoDeCerveja(Estilo tipoCerveja, Model model, RedirectAttributes redirectAttributes){
        System.out.println(tipoCerveja);
        estiloService.save(tipoCerveja);
        redirectAttributes.addFlashAttribute("sucesso", true);
        redirectAttributes.addFlashAttribute("message", "Estilo Editado com sucesso!");

        return "redirect:index";
    }

    @PostMapping("/addTipoCerveja")
    public String adicionarTipoDeCerveja(Estilo tipoCerveja, Model model, RedirectAttributes redirectAttributes){
        System.out.println(tipoCerveja.getNome());
        estiloService.save(tipoCerveja);
        redirectAttributes.addFlashAttribute("sucesso", true);
        redirectAttributes.addFlashAttribute("message", "Estilo Salvo com sucesso!");

        return "redirect:index";
    }
    @GetMapping("/remover-cerveja/{id}")
    public RedirectView removerCerveja(@PathVariable("id") Long id, Model model,RedirectAttributes redirectAttributes){

        model.addAttribute("module", "tipoCerveja");
        System.out.println(id);
        try{
            estiloService.deleteById(id);
            redirectAttributes.addFlashAttribute("sucessoDelete",true);
        }catch (Exception ex){
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("sucessoDelete",false);
        }
        return new RedirectView("../index");
    }


}
