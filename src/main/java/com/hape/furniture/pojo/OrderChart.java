package com.hape.furniture.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class OrderChart {
    private List<Chart> order;
}
