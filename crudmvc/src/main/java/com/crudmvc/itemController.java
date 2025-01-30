package com.crudmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itens")
public class itemController {

    @Autowired
    private itemRepository itemRepository;

    // Listar todos os itens
    @GetMapping("/listar")
    public List<item> listar() {
        return itemRepository.findAll();
    }

    // Buscar item pelo nome (agora mantido corretamente no Controller)
    @GetMapping("/buscar")
    public item buscar(@RequestParam String nome) {
        return itemRepository.findByNome(nome)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado."));
    }

    // Cadastrar um item
    @PostMapping("/cadastrar")
    public item cadastrar(@RequestBody item item) {
        return itemRepository.salvarItemComValidacao(item);
    }

    // Remover um item pelo nome
    @DeleteMapping("/remover")
    public String remover(@RequestParam String nome) {
        try {
            itemRepository.removerItemPorNome(nome);
            return "Item removido com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
