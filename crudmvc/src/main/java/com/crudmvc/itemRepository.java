package com.crudmvc;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface itemRepository extends JpaRepository<item, Long> {

    // Verifica se existe um item pelo nome
    Optional<item> findByNome(String nome);

    // Método para salvar um item com validações
    default item salvarItemComValidacao(item item) {
        if (item.getNome() == null || item.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do item não pode ser nulo ou vazio.");
        }

        if (findByNome(item.getNome()).isPresent()) {
            throw new IllegalArgumentException("Já existe um item com o mesmo nome.");
        }

        return save(item);
    }

    // Remover um item pelo nome
    @Transactional
    default void removerItemPorNome(String nome) {
        findByNome(nome).ifPresentOrElse(
            this::delete,
            () -> { throw new IllegalArgumentException("Item não encontrado."); }
        );
    }
}
