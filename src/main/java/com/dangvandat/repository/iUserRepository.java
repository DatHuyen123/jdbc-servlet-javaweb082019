package com.dangvandat.repository;

import com.dangvandat.Entity.UserEntity;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface iUserRepository extends GernericJDBC<UserEntity>{
	Long insert(UserEntity userEntity);
	List<UserEntity> findAll(UserEntity userEntity , Pageble pageble);
}
