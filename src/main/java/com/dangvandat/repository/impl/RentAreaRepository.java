package com.dangvandat.repository.impl;

import com.dangvandat.Entity.RentArea;
import com.dangvandat.repository.IRentAreaRepository;

public class RentAreaRepository extends AbstractJDBC<RentArea> implements IRentAreaRepository {

    @Override
    public void deleteByBuildingId(Long id) {
        String where = "WHERE buildingid = "+id+"";
        this.deleteByProperty(where);
    }
}
