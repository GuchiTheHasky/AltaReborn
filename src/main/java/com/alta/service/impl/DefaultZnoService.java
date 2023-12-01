package com.alta.service.impl;

import com.alta.entity.Zno;
import com.alta.exception.ZnoException;
import com.alta.repository.ZnoRepository;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public void delete(int id) {
        znoRepository.findById(id).ifPresent(zno -> znoRepository.deleteById(id));
    }

    @Override
    public Zno update(int id, Zno zno) {
        return znoRepository.findById(id)
                .map(znoRequired -> {
                    znoRequired.setName(zno.getName());
                    znoRequired.setYear(zno.getYear());
                    return znoRepository.save(znoRequired);
                })
                .orElseThrow(() -> new ZnoException(id));
    }
}

