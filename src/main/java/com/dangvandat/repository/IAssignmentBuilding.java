package com.dangvandat.repository;

import com.dangvandat.Entity.AssignmentBuildingEntity;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface IAssignmentBuilding extends GernericJDBC<AssignmentBuildingEntity> {
    List<AssignmentBuildingEntity> findAll(AssignmentBuildingEntity assignmentBuildingEntity ,Pageble pageble);
    int countByProperty(AssignmentBuildingEntity assignmentBuildingEntity);
    void deleteByStaffId(AssignmentBuildingEntity assignmentBuildingEntity);
}
