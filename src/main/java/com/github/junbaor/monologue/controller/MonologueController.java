package com.github.junbaor.monologue.controller;

import com.github.junbaor.monologue.data.PutMonologue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MonologueController {

    @PutMapping("/monologue")
    public ResponseEntity<String> putMonologue(@RequestBody PutMonologue putMonologue) {
        log.debug("参数:{}", putMonologue);
        return new ResponseEntity<>("创建成功", HttpStatus.OK);
    }

}
