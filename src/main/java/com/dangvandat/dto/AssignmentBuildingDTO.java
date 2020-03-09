package com.dangvandat.dto;

public class AssignmentBuildingDTO extends AbstractDTO<AssignmentBuildingDTO>{

    private Long buildingId;
    private Long staffId;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}


