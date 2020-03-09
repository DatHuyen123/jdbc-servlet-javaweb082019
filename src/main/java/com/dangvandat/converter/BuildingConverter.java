package com.dangvandat.converter;

import com.dangvandat.Entity.BuildingEntity;
import com.dangvandat.Entity.RentArea;
import com.dangvandat.dto.BuildingDTO;
import com.dangvandat.paging.impl.PageRequest;
import com.dangvandat.repository.IRentAreaRepository;
import com.dangvandat.repository.impl.RentAreaRepository;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildingConverter {

    //@Inject
    private IRentAreaRepository rentAreaRepository = new RentAreaRepository();


	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		@SuppressWarnings("unused")
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
        Map<String , Object> condition = new HashMap<>();
        condition.put("buildingid" , buildingEntity.getId());
        /*List<RentArea> rentAreas = repository.findAll(condition,new PageRequest(null,null,null));
        List<String> rents = new ArrayList<>();
        for(RentArea rens : rentAreas){
            rents.add(rens.getValue());
        }
        if(rents.size() > 0){
            result.setRentArea(StringUtils.join(rents,","));
        }*/
        List<String> rents = rentAreaRepository.findAll(condition,new PageRequest(null,null,null))
                                             .stream().map(RentArea::getValue).collect(Collectors.toList());
        if(rents.size() > 0){
            result.setRentArea(StringUtils.join(rents,","));
        }
		return result;
	}
	
	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		@SuppressWarnings("unused")
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		if(StringUtils.isNotBlank(buildingDTO.getNumberOfBasement())){
            result.setNumberOfBasement(Integer.parseInt(buildingDTO.getNumberOfBasement()));
        }
        if(StringUtils.isNotBlank(buildingDTO.getBuildingArea())){
            result.setBuildingArea(Integer.parseInt(buildingDTO.getBuildingArea()));
        }
		return result;
	}
	
}
