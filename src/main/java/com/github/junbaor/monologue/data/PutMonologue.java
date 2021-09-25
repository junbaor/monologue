package com.github.junbaor.monologue.data;

import com.github.junbaor.monologue.enums.Source;
import lombok.Data;

@Data
public class PutMonologue {

    private String content;
    private Source source;

}
