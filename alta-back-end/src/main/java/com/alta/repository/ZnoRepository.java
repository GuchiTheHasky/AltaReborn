package com.alta.repository;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZnoRepository extends JpaRepository<Zno, Integer> {

    Zno findByName(String name);
}
