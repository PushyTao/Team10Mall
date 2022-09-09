package com.hape.furniture.controller;

import com.hape.furniture.pojo.FoRecord;
import com.hape.furniture.service.FoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/record")
public class FoRecordController {
    @Autowired
    private FoRecordService foRecordService;

    @PostMapping("/add")
    public boolean add(FoRecord foRecord){
        return foRecordService.add(foRecord);
    }
}
