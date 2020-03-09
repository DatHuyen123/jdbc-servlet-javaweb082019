package com.dangvandat.service;

import com.dangvandat.dto.AssignmentBuildingDTO;
import com.dangvandat.dto.UserDTO;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface IAssignmentService  {
    List<UserDTO> saveAssignment(AssignmentBuildingDTO assignmentBuildingDTO);
    List<UserDTO> findAllUser(AssignmentBuildingDTO assignmentBuildingDTO , UserDTO userSearchDTO , Pageble pageble);
}
