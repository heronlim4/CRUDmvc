package com.crudmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itens")
public class itemController {

    @Autowired
    private itemRepository itemRepository;

    @PostMapping("/cadastrar")
    public item cadastrar(@RequestBody item item) {
        return itemRepository.save(item);
    }

    @GetMapping("/listar")
    public List<item> listar() {
        return itemRepository.findAll();
    }

    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/buscar/{id}")
    public item buscar(@PathVariable Long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
