package com.github.junbaor.monologue.service;

import com.github.junbaor.monologue.data.PutMonologue;
import com.github.junbaor.monologue.entity.Monologue;
import com.github.junbaor.monologue.repository.MonologueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class MonologueService {

    private final MonologueRepository monologueRepository;

    public Monologue save(PutMonologue putMonologue) {
        Monologue monologue = new Monologue();
        monologue.setContent(putMonologue.getContent());
        monologue.setSource(putMonologue.getSource());

        return monologueRepository.save(monologue);
    }

    public Page<Monologue> getMonologue(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(DESC, "id"));
        return monologueRepository.findAll(pageRequest);
    }

}
