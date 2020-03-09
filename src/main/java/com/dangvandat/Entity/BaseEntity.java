package com.dangvandat.Entity;

import java.sql.Timestamp;

import com.dangvandat.annotation.Column;

public class BaseEntity {
	
	@Column(name = "id")
	public Long id;
	
	@Column(name = "createddate")
    public Timestamp createdDate;
	
	@Column(name = "modifieddate")
    public Timestamp modifiedDate;
	
	@Column(name = "createdby")
    public String createdBy;
	
	@Column(name = "modifiedby")
    public String modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	} 
	
	
}
