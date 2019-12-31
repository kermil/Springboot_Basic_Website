package com.example.test.domain.repository;

import com.example.test.domain.Entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
