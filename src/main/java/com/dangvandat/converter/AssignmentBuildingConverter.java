package com.dangvandat.converter;

import com.dangvandat.Entity.AssignmentBuildingEntity;
import com.dangvandat.dto.AssignmentBuildingDTO;
import org.modelmapper.ModelMapper;

public class AssignmentBuildingConverter {

    public AssignmentBuildingDTO convertToDTO(AssignmentBuildingEntity assignmentBuildingEntity){
        ModelMapper modelMapper = new ModelMapper();
        AssignmentBuildingDTO result = modelMapper.map(assignmentBuildingEntity , AssignmentBuildingDTO.class);
        return result;
    }

    public AssignmentBuildingEntity convertToEntity(AssignmentBuildingDTO assignmentBuildingDTO){
        ModelMapper modelMapper = new ModelMapper();
        AssignmentBuildingEntity result = modelMapper.map(assignmentBuildingDTO , AssignmentBuildingEntity.class);
        return result;
    }
}
