package com.github.junbaor.monologue.repository;

import com.github.junbaor.monologue.entity.Monologue;
import com.github.junbaor.monologue.enums.Source;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@Transactional
@SpringBootTest
class MonologueRepositoryTest {

    @Autowired
    MonologueRepository repository;

    @Test
    void findById() {
        Monologue monologue = new Monologue();
        monologue.setContent("今天是个好日子");
        monologue.setSource(Source.web);
        monologue = repository.save(monologue);

        Optional<Monologue> optional = repository.findById(Objects.requireNonNull(monologue.getId()));
        assertThat(optional).hasValue(monologue);
    }

    @Test
    void findSort() {
        Monologue monologue1 = new Monologue();
        monologue1.setContent("我是第1条信息");
        repository.save(monologue1);

        Monologue monologue2 = new Monologue();
        monologue2.setContent("我是第2条信息");
        repository.save(monologue2);

        Monologue monologue3 = new Monologue();
        monologue3.setContent("我是第3条信息");
        repository.save(monologue3);

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(DESC, "id"));
        Page<Monologue> monologues = repository.findAll(pageRequest);
        assertThat(monologues).containsExactly(monologue3, monologue2, monologue1);
    }

    @Test
    void findByIdLessThan() {
        Monologue monologue1 = new Monologue();
        monologue1.setContent("我是第1条信息");
        repository.save(monologue1);

        Monologue monologue2 = new Monologue();
        monologue2.setContent("我是第2条信息");
        repository.save(monologue2);

        Monologue monologue3 = new Monologue();
        monologue3.setContent("我是第3条信息");
        repository.save(monologue3);

        PageRequest pageRequest = PageRequest.ofSize(2).withSort(Sort.by(DESC, "id"));
        List<Monologue> monologues = repository.findByIdLessThan(monologue3.getId(), pageRequest);
        assertThat(monologues).containsExactly(monologue2, monologue1);
    }

}
