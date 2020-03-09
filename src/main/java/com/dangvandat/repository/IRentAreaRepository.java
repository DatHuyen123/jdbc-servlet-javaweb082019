package com.dangvandat.repository;

import com.dangvandat.Entity.RentArea;

public interface IRentAreaRepository extends GernericJDBC<RentArea> {
    void deleteByBuildingId(Long id);
}
