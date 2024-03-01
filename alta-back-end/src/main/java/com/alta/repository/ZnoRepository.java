package com.alta.repository;

import com.alta.entity.Zno;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Zno} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
@NonNullApi
public interface ZnoRepository extends JpaRepository<Zno, Integer> {
    Page<Zno> findAll(Pageable pageable);
}
