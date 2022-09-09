package com.hape.furniture.pojo;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component
@Data
public class Chart {
    private Integer value;
    private String name;
}
