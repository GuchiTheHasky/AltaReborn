package com.alta.service.impl;

import com.alta.entity.Zno;
import com.alta.exception.NoEntityException;
import com.alta.repository.ZnoRepository;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultZnoService implements ZnoService {
    private final ZnoRepository znoRepository;

    @Override
    public List<Zno> findAll() {
        return znoRepository.findAll();
    }

    @Override
    public Zno save(Zno zno) {
        return znoRepository.save(zno);
    }

    @Override
    public Zno delete(Integer id) {
        Zno znoById = findById(id);
        znoRepository.deleteById(id);
        return znoById;
    }

    @Override
    public Zno update(Integer id, Zno zno) {
        Zno znoRequired = findById(id);
        znoRequired.setName(zno.getName());
        znoRequired.setYear(zno.getYear());
        return znoRepository.save(znoRequired);
    }

    Zno findById(int id) {
        Optional<Zno> znoById = znoRepository.findById(id);
        Zno zno;
        try {
            zno = znoById.orElseThrow(() -> new NoEntityException(id));
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }
        return zno;
    }

}

