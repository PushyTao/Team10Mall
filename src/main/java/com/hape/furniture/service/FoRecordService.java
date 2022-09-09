package com.hape.furniture.service;

import com.hape.furniture.pojo.FoRecord;
import org.springframework.stereotype.Service;

@Service
public interface FoRecordService {
    boolean add(FoRecord foRecord);
}
