package com.hape.furniture.dao;

import com.hape.furniture.pojo.FoRecord;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoRecordDao {
    int insert(FoRecord foRecord);
    List<FoRecord> findByOid(int oid);
    List<FoRecord> findAll();
    boolean del(int id);
}
