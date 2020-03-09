package com.dangvandat.api;

import com.dangvandat.dto.AssignmentBuildingDTO;
import com.dangvandat.dto.UserDTO;
import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.impl.PageRequest;
import com.dangvandat.repository.IAssignmentBuilding;
import com.dangvandat.service.IAssignmentService;
import com.dangvandat.service.impl.AssignmentBuildingService;
import com.dangvandat.utils.HttpUtil;
import com.dangvandat.utils.formUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-assignmentbuilding"})
public class AssignmentBuildingAPI extends HttpServlet {

    private IAssignmentService assignmentService;

    public AssignmentBuildingAPI(){
        if(assignmentService == null){
            assignmentService = new AssignmentBuildingService();
        }
    }

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        AssignmentBuildingDTO model = formUtil.toModel(AssignmentBuildingDTO.class , request);
        UserDTO userDTO = formUtil.toModel(UserDTO.class , request);
        Pageble pageble = new PageRequest(null , null , null);
        userDTO.setListResult(assignmentService.findAllUser(model , userDTO , pageble));
        mapper.writeValue(response.getOutputStream() , userDTO.getListResult());
    }

    protected void doPost(HttpServletRequest request ,HttpServletResponse response) throws ServletException , IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        AssignmentBuildingDTO model = HttpUtil.of(request.getReader()).toModel(AssignmentBuildingDTO.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setListResult(assignmentService.saveAssignment(model));
        mapper.writeValue(response.getOutputStream() , userDTO.getListResult());
    }

    protected void doDelete(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{

    }

}
