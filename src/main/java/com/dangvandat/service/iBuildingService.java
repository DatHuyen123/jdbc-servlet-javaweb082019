package com.dangvandat.service;

import com.dangvandat.Builder.BuildingSearchBuilder;
import com.dangvandat.dto.BuildingDTO;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface iBuildingService {
	BuildingDTO save(BuildingDTO newBuildingDTO);
    void update(BuildingDTO updateBuildingDTO , Long id);
    void delete(Long[] ids);
	List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder , Pageble pageble);
	int getTotalItems(BuildingSearchBuilder builder);
	BuildingDTO finById(Long id);
}
