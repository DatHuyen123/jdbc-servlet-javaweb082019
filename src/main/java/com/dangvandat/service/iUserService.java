package com.dangvandat.service;

import com.dangvandat.dto.UserDTO;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface iUserService {
	UserDTO save(UserDTO newUser);
	List<UserDTO> findAll(UserDTO userDTO , Pageble pageble);
}
