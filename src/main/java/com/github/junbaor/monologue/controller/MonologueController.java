package com.github.junbaor.monologue.controller;

import com.github.junbaor.monologue.data.PutMonologue;
import com.github.junbaor.monologue.entity.Monologue;
import com.github.junbaor.monologue.service.MonologueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MonologueController {

    private final MonologueService monologueService;

    @PutMapping("/monologue")
    public ResponseEntity<Monologue> putMonologue(@Valid @RequestBody PutMonologue putMonologue) {
        log.debug("putMonologue 参数:{}", putMonologue);

        Monologue monologue = monologueService.save(putMonologue);
        return new ResponseEntity<>(monologue, HttpStatus.OK);
    }

    @GetMapping("/monologue")
    public ResponseEntity<List<Monologue>> getMonologue(
            @RequestParam(value = "page", defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(value = "size", defaultValue = "20") @Max(value = 50) Integer size) {
        log.debug("getMonologue 参数: page:{} size:{}", page, size);

        Page<Monologue> monologues = monologueService.getMonologue(page, size);
        return new ResponseEntity<>(monologues.getContent(), HttpStatus.OK);
    }

}
