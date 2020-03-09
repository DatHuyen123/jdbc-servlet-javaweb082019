package com.dangvandat.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dangvandat.dto.BuildingDTO;
import com.dangvandat.service.impl.BuildingService;
import com.dangvandat.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dangvandat.service.iBuildingService;

@WebServlet(urlPatterns = {"/api-building"})
public class BuildingAPI extends HttpServlet {

	private static final long serialVersionUID = -7725744656492434889L;

    /*@Inject*/
	private iBuildingService buildingService;

	public BuildingAPI(){
		if(buildingService == null){
			buildingService = new BuildingService();
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		//logic
		buildingDTO = buildingService.save(buildingDTO);
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
          ObjectMapper mapper = new ObjectMapper();
          request.setCharacterEncoding("UTF-8");
          response.setContentType("application/json");
          BuildingDTO updateBuildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
          //logic
          buildingService.update(updateBuildingDTO , updateBuildingDTO.getId());
          mapper.writeValue(response.getOutputStream(), "{}");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse
	        response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
        //logic
        buildingService.delete(buildingDTO.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
	}
}
