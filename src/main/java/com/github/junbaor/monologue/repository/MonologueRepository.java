package com.github.junbaor.monologue.repository;

import com.github.junbaor.monologue.entity.Monologue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MonologueRepository extends PagingAndSortingRepository<Monologue, Long> {

    List<Monologue> findByIdLessThan(Long id, Pageable pageable);

}
