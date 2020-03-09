package com.dangvandat.enums;

public enum District {
	QUAN_THANH_XUAN("Quan Thanh Xuan"),
    QUAN_DONG_DA("Quan Dong Da"),
    QUAN_HAI_BA_TRUNG("Quan Hai Ba Trung"),
    QUAN_CAU_GIAY("Quan Cau Giay"),
    QUAN_BA_DINH("Quan Ba Dinh"),
    QUAN_HOAN_KIEM("Quan Hoan Kiem"),
    QUAN_TAY_HO("Quan Tay Ho"),
    QUAN_LONG_BIEN("Quan Long Bien"),
    QUAN_HOANG_MAI("Quan Hoang Mai"),
    QUAN_NAM_TU_LIEM("Quan Nam Tu Liem"),
    QUAN_BAC_TU_LIEM("Quan Bac Tu Liem"),
    QUAN_HA_DONG("Quan Ha Dong");

    private String value;

    District(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}    
}
