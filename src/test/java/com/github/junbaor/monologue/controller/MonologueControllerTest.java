package com.github.junbaor.monologue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.junbaor.monologue.data.PutMonologue;
import com.github.junbaor.monologue.entity.Monologue;
import com.github.junbaor.monologue.enums.Source;
import com.github.junbaor.monologue.service.MonologueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MonologueController.class)
class MonologueControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MonologueService monologueService;

    @BeforeEach
    public void before() {
        Monologue monologue = new Monologue();
        monologue.setContent("mock 数据");
        monologue.setSource(Source.web);
        Mockito.when(monologueService.save(Mockito.any())).thenReturn(monologue);

        Page<Monologue> page = new PageImpl<>(List.of(monologue));
        Mockito.when(monologueService.getMonologue(Mockito.any(), Mockito.any())).thenReturn(page);
    }

    @Test
    public void putMonologue() throws Exception {
        PutMonologue putMonologue = new PutMonologue();
        putMonologue.setContent("今天是个好日子");
        putMonologue.setSource(Source.web);

        String json = objectMapper.writeValueAsString(putMonologue);

        this.mockMvc
                .perform(put("/monologue").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8).content(json))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("创建成功")));

        this.mockMvc
                .perform(put("/monologue"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getMonologue() throws Exception {
        this.mockMvc
                .perform(get("/monologue"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc
                .perform(put("/monologue").param("page", "-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        this.mockMvc
                .perform(put("/monologue").param("size", "51"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
