package com.crudmvc.model.regrasDeNegocio;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crudmvc.model.entidade.item;
import com.crudmvc.model.repositorio.itemRepository;

@Service
public class itemServices {

    private final itemRepository repositorio;
    private final itemConstraints regraNegocio;

    public itemServices(itemRepository repositorio, itemConstraints regraNegocio) {
        this.repositorio = repositorio;
        this.regraNegocio = regraNegocio;
    }

    public item cadastrarItem(item item) {
        return regraNegocio.validarESalvar(item);
    }

    public List<item> listarItens() {
        return repositorio.findAll();
    }

    public item buscarPorNome(String nome) {
        return repositorio.findByNome(nome).orElse(null);
    }

    public void removerPorNome(String nome) {
        regraNegocio.removerPorNome(nome);
    }

    // Método para editar item (delegado para a regra de negócio)
    public item editarItem(Long id, item itemAtualizado) {
        return regraNegocio.editarItem(id, itemAtualizado);
    }
}

