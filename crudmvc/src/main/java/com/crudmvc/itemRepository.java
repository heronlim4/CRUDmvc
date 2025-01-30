package com.crudmvc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface itemRepository extends JpaRepository<item, Long> {
}
