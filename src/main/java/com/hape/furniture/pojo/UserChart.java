package com.hape.furniture.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
public class UserChart {
    private List<Chart> gender;
    private List<Chart> city;
    private List<Chart> birthday;
}
