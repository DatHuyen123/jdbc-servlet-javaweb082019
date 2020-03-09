package com.dangvandat.service.impl;

import com.dangvandat.Entity.AssignmentBuildingEntity;
import com.dangvandat.Entity.UserEntity;
import com.dangvandat.converter.AssignmentBuildingConverter;
import com.dangvandat.converter.UserConverter;
import com.dangvandat.dto.AssignmentBuildingDTO;
import com.dangvandat.dto.UserDTO;
import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.impl.PageRequest;
import com.dangvandat.repository.IAssignmentBuilding;
import com.dangvandat.repository.impl.AssignmentBuildingRepository;
import com.dangvandat.repository.impl.UserRepository;
import com.dangvandat.service.IAssignmentService;
import com.dangvandat.repository.iUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssignmentBuildingService implements IAssignmentService {

    private IAssignmentBuilding assignmentBuilding = new AssignmentBuildingRepository();

    private iUserRepository userRepository = new UserRepository();

    private AssignmentBuildingConverter assignmentBuildingConverter = new AssignmentBuildingConverter();

    private UserConverter userConverter = new UserConverter();

    @Override
    public List<UserDTO> saveAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        long buildingId = assignmentBuildingDTO.getBuildingId();
        List<AssignmentBuildingDTO> listAssignmentBuildingDTO = assignmentBuilding.findAll(assignmentBuildingConverter.convertToEntity(assignmentBuildingDTO) , new PageRequest(null , null , null))
                .stream().map(item -> assignmentBuildingConverter.convertToDTO(item)).collect(Collectors.toList());
        boolean flag = false;
        if(listAssignmentBuildingDTO.size() > 0){
            for(AssignmentBuildingDTO as : listAssignmentBuildingDTO){
                for(int i = 0 ; i < assignmentBuildingDTO.getIds().length ; i++){
                    if(as.getStaffId() == assignmentBuildingDTO.getIds()[i]){
                        assignmentBuildingDTO.getIds()[i] = 0L;
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    as.setStaffId(0L);
                    flag = false;
                }
            }
            for(AssignmentBuildingDTO as : listAssignmentBuildingDTO){
                if(as.getStaffId() != 0L){
                    assignmentBuilding.deleteByStaffId(assignmentBuildingConverter.convertToEntity(as));
                }
            }
            for(long item : assignmentBuildingDTO.getIds()){
                if(item != 0L){
                    assignmentBuildingDTO.setStaffId(item);
                    assignmentBuildingDTO.setBuildingId(buildingId);
                    assignmentBuilding.insert(assignmentBuildingConverter.convertToEntity(assignmentBuildingDTO));
                }
            }
        }else{
            for(long item : assignmentBuildingDTO.getIds()){
                if(item != 0L){
                    assignmentBuildingDTO.setStaffId(item);
                    assignmentBuildingDTO.setBuildingId(buildingId);
                    assignmentBuilding.insert(assignmentBuildingConverter.convertToEntity(assignmentBuildingDTO));
                }
            }
        }
        assignmentBuildingDTO.setBuildingId(buildingId);
        assignmentBuildingDTO.setStaffId(null);
        return findAllUser(assignmentBuildingDTO , new UserDTO() , new PageRequest(null , null , null));
    }

    @Override
    public List<UserDTO> findAllUser(AssignmentBuildingDTO assignmentBuildingDTO , UserDTO userSearchDTO, Pageble pageble) {
        AssignmentBuildingEntity assignmentBuildingEntity = assignmentBuildingConverter.convertToEntity(assignmentBuildingDTO);
        List<AssignmentBuildingDTO> resultAssignmentBuilding = assignmentBuilding.findAll(assignmentBuildingEntity , pageble)
                .stream().map(item -> assignmentBuildingConverter.convertToDTO(item)).collect(Collectors.toList());
        UserEntity userEntity = userConverter.convertToEntity(userSearchDTO);
        List<UserEntity> resultUserEntities = userRepository.findAll(userEntity , pageble);
        List<UserDTO> resultUserDTO = resultUserEntities.stream().map(item -> userConverter.convertToDTO(item)).collect(Collectors.toList());
        for(UserDTO userDTO : resultUserDTO){
            for(AssignmentBuildingDTO assignmentBuilding : resultAssignmentBuilding){
                if(userDTO.getId() == assignmentBuilding.getStaffId()){
                    userDTO.setCheck("checked");
                }
            }
        }
        return resultUserDTO;
    }
}
