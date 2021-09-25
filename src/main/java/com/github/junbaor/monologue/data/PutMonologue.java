package com.github.junbaor.monologue.data;

import com.github.junbaor.monologue.enums.Source;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PutMonologue {

    @NotBlank
    private String content;
    @NotNull
    private Source source;

}
