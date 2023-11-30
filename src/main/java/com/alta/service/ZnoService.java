package com.alta.service;

import com.alta.entity.Zno;
import java.util.List;

public interface ZnoService {
    List<Zno> findAll();
    Zno save(Zno zno);
    Zno delete(Integer id);
    Zno update(Integer id, Zno zno);
}
