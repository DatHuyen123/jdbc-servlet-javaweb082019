package com.dangvandat.repository.impl;

import com.dangvandat.Entity.UserEntity;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.iUserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AbstractJDBC<UserEntity> implements iUserRepository{

	@Override
	public Long insert(UserEntity userEntity) {
		
		return null;
	}

	@Override
	public List<UserEntity> findAll(UserEntity userEntity, Pageble pageble) {
		Map<String , Object> mapSearch = new HashMap<>();
		return findAll(mapSearch , pageble);
	}

}
