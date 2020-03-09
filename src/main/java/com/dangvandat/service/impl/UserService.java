package com.dangvandat.service.impl;

import com.dangvandat.Entity.UserEntity;
import com.dangvandat.converter.UserConverter;
import com.dangvandat.dto.UserDTO;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.impl.UserRepository;
import com.dangvandat.service.iUserService;
import com.dangvandat.repository.iUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements iUserService{

	private iUserRepository userRepository = new UserRepository();

	private UserConverter userConverter = new UserConverter();

	@Override
	public UserDTO save(UserDTO newUser) {
		UserConverter converter = new UserConverter();
		@SuppressWarnings("unused")
		UserEntity userEntity = converter.convertToEntity(newUser);
		
		return null;
	}

	@Override
	public List<UserDTO> findAll(UserDTO userDTO, Pageble pageble) {
		UserEntity userEntity = userConverter.convertToEntity(userDTO);
		List<UserEntity> resultUserEntities = userRepository.findAll(userEntity , pageble);
		List<UserDTO> resultUserDTO = resultUserEntities.stream().map(item -> userConverter.convertToDTO(item)).collect(Collectors.toList());
		return null;
	}

}
