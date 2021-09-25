package com.github.junbaor.monologue.entity;

import com.github.junbaor.monologue.enums.Source;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Monologue extends AbstractPersistable<Long> {

    private String content;
    private Source source;

}
