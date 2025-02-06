package com.crudmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crudmvc.model.entidade.item;
import com.crudmvc.model.regrasDeNegocio.itemServices;

@Controller
public class itemController {

    private final itemServices itemServices;

    public itemController(itemServices itemServices) {
        this.itemServices = itemServices;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("itens", itemServices.listarItens());
        return "index";
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastrar";
    }

    @PostMapping("/api/itens/cadastrar")
    public String cadastrarItem(@RequestBody item item) {
        itemServices.cadastrarItem(item);
        return "redirect:/";
    }

    @GetMapping("/editar/{nome}")
    public String editar(@PathVariable String nome, Model model) {
        item item = itemServices.buscarPorNome(nome);
        model.addAttribute("item", item);
        return "editar";
    }

    @PostMapping("/api/itens/editar/{nome}")
    public String editarItem(@PathVariable long id, @RequestBody item item) {
        itemServices.editarItem(id, item);
        return "redirect:/";
    }

    @DeleteMapping("/api/itens/remover/{nome}")
    public String removerItem(@PathVariable String nome) {
        itemServices.removerPorNome(nome);
        return "redirect:/";
    }

    @GetMapping("/api/itens/buscar/{nome}")
    public @ResponseBody item buscarItem(@PathVariable String nome) {
        return itemServices.buscarPorNome(nome);
    }
}

