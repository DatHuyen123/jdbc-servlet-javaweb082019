package com.dangvandat.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dangvandat.dto.UserDTO;
import com.dangvandat.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-user"})
public class UserAPI extends HttpServlet {

	private static final long serialVersionUID = -7725744656492434889L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		//xu ly logic

		mapper.writeValue(response.getOutputStream(), userDTO);
	}
	
	/*
	 * protected void doPut(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * }
	 * 
	 * protected void doDelete(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * }
	 */
	
}
