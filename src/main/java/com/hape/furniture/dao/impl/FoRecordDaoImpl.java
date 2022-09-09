package com.hape.furniture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hape.furniture.dao.FoRecordDao;
import com.hape.furniture.pojo.FoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoRecordDaoImpl implements FoRecordDao {
    @Autowired
    private FoRecord foRecord;
    @Override
    public int insert(FoRecord foRecord) {
        boolean flag = foRecord.insert();
        if(flag){
           return foRecord.getId();
        }
        return 0;
    }

    @Override
    public List<FoRecord> findByOid(int oid) {
        QueryWrapper<FoRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("oid",oid);
        return foRecord.selectList(wrapper);
    }

    @Override
    public List<FoRecord> findAll() {
        return foRecord.selectAll();
    }

    @Override
    public boolean del(int id) {
        foRecord.setId(id);
        return foRecord.deleteById();
    }
}
