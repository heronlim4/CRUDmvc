package com.crudmvc.model.regrasDeNegocio;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.crudmvc.model.entidade.item;
import com.crudmvc.model.repositorio.itemRepository;

@Component
public class itemConstraints {
    
    private final itemRepository repositorio;

    public itemConstraints(itemRepository repositorio) {
        this.repositorio = repositorio;
    }

    public item validarESalvar(item item) {
        if (item.getNome() == null || item.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do item não pode ser nulo ou vazio.");
        }
        if (repositorio.findByNome(item.getNome()).isPresent()) {
            throw new IllegalArgumentException("Já existe um item com esse nome.");
        }
        return repositorio.save(item);
    }

    public void removerPorNome(String nome) {
        Optional<item> itemEncontrado = repositorio.findByNome(nome);
        if (itemEncontrado.isEmpty()) {
            throw new IllegalArgumentException("Item não encontrado.");
        }
        repositorio.delete(itemEncontrado.get());
    }

    public item editarItem(Long id, item itemAtualizado) {
        Optional<item> itemExistente = repositorio.findById(id);
        if (itemExistente.isEmpty()) {
            throw new IllegalArgumentException("Item não encontrado para edição.");
        }

        item item = itemExistente.get();
        item.setNome(itemAtualizado.getNome());
        item.setDescricao(itemAtualizado.getDescricao());
        item.setPreco(itemAtualizado.getPreco());

        return repositorio.save(item);
    }
}
