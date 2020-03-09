package com.dangvandat.repository;

import com.dangvandat.Builder.BuildingSearchBuilder;
import com.dangvandat.Entity.BuildingEntity;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface iBuildingRepository extends GernericJDBC<BuildingEntity> {
    List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageble pageble);
    int countByProperty(BuildingSearchBuilder builder);
}
