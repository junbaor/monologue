package com.github.junbaor.monologue.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.junbaor.monologue.data.PutMonologue;
import com.github.junbaor.monologue.enums.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonologueController.class)
class MonologueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        PutMonologue putMonologue = new PutMonologue();
        putMonologue.setContent("今天是个好日子");
        putMonologue.setSource(Source.web);

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(putMonologue);

        this.mockMvc
                .perform(put("/monologue").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("创建成功")));
    }

}
