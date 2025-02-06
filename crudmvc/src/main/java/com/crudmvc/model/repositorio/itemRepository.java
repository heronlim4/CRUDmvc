package com.crudmvc.model.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudmvc.model.entidade.item;

public interface itemRepository extends JpaRepository<item, Long> {
    Optional<item> findByNome(String nome);
}