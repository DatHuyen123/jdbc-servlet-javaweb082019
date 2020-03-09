package com.dangvandat.enums;

public enum BuildingType {
	TANG_TRET("Tang Tret"),
    NGUYEN_CAN("Nguyen Can"),
    NOI_THAT("Noi That");

    private String value;

    BuildingType(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}    
}
