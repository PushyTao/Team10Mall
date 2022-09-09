package com.hape.furniture.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReturnInfo {
    private boolean status;
    private String msg;
}
