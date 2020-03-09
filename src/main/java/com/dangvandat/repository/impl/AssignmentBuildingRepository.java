package com.dangvandat.repository.impl;

import com.dangvandat.Entity.AssignmentBuildingEntity;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.IAssignmentBuilding;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentBuildingRepository extends AbstractJDBC<AssignmentBuildingEntity> implements IAssignmentBuilding {
    @Override
    public List<AssignmentBuildingEntity> findAll(AssignmentBuildingEntity assignmentBuildingEntity, Pageble pageble) {
        Map<String , Object> mapSearch = builMapSearch(assignmentBuildingEntity);
        StringBuilder whereClause = builStringWhereClause(assignmentBuildingEntity);
        return findAll(mapSearch , pageble , whereClause.toString());
    }

    @Override
    public int countByProperty(AssignmentBuildingEntity assignmentBuildingEntity) {
        Map<String , Object> mapSearch = builMapSearch(assignmentBuildingEntity);
        return countByProperty(mapSearch);
    }

    @Override
    public void deleteByStaffId(AssignmentBuildingEntity assignmentBuildingEntity) {
        String whereClause = "WHERE staffid = " + assignmentBuildingEntity.getStaffId() +" AND buildingid = " +assignmentBuildingEntity.getBuildingId()+"";
        this.deleteByProperty(whereClause);
    }

    private StringBuilder builStringWhereClause(AssignmentBuildingEntity assignmentBuildingEntity) {
        StringBuilder result = new StringBuilder("");
        if(assignmentBuildingEntity.getStaffId() != null && assignmentBuildingEntity.getStaffId() > 0){
            result.append(" AND staffid = " + assignmentBuildingEntity.getStaffId() + "");
        }
        return result;
    }

    private Map<String , Object> builMapSearch(AssignmentBuildingEntity assignmentBuildingEntity){
        Map<String , Object> result = new HashMap<>();
        try {
            Field[] fields = AssignmentBuildingEntity.class.getDeclaredFields();
            for(Field field : fields){
                if(!field.getName().startsWith("staff")){
                    field.setAccessible(true);
                    if(!field.get(assignmentBuildingEntity).toString().equals("0")){
                        result.put(field.getName().toLowerCase() , field.get(assignmentBuildingEntity));
                    }
                }
            }
        }catch (IllegalArgumentException | IllegalAccessException e){
            e.printStackTrace();
        }
        return result;
    }
}
