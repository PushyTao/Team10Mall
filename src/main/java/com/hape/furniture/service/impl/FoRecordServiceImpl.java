package com.hape.furniture.service.impl;

import com.hape.furniture.dao.FoRecordDao;
import com.hape.furniture.pojo.FoRecord;
import com.hape.furniture.service.FoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FoRecordServiceImpl implements FoRecordService {
    @Autowired
    private FoRecordDao foRecordDao;

    @Override
    public boolean add(FoRecord foRecord) {
        return foRecordDao.insert(foRecord)!=0;
    }
}
