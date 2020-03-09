package com.dangvandat.controller;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dangvandat.Builder.BuildingSearchBuilder;
import com.dangvandat.dto.BuildingDTO;
import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.Sorter;
import com.dangvandat.paging.impl.PageRequest;
import  com.dangvandat.service.iBuildingService;
import com.dangvandat.utils.DataUtil;
import com.dangvandat.utils.formUtil;
import org.apache.commons.lang.StringUtils;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {

	private static final long serialVersionUID = 8084128967329011577L;

	@Inject
	private iBuildingService buildingService;

	/*public BuildingController(){
	    if(buildingService == null){
            buildingService = new BuildingService();
        }

    }*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        BuildingDTO model = formUtil.toModel(BuildingDTO.class , request);
	    String action = request.getParameter("action");
	    String url = "";
	    if(action.equals("LIST")){
            url = "views/admin/building/list.jsp";
            BuildingSearchBuilder builder = initBuildingBuider(model);
            Pageble pageble = new PageRequest(model.getPage() , model.getMaxPageItem() ,new Sorter(model.getSortName() , model.getSortBy()));
            model.setTotalItems(buildingService.getTotalItems(builder));
            model.setTotalPage((int) Math.ceil((double) model.getTotalItems() / model.getMaxPageItem()));
            model.setListResult(buildingService.findAll(builder , pageble));
        }else if(action.equals("EDIT")){
	        if(model.getId() != null){
                model = buildingService.finById(model.getId());
            }
            url = "views/admin/building/edit.jsp";
        }
        request.setAttribute("districts" , DataUtil.getDistrict());
        request.setAttribute("buildingTypes" , DataUtil.getBuildingType());
        request.setAttribute("model" , model);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
    private BuildingSearchBuilder initBuildingBuider(BuildingDTO model) {
        BuildingSearchBuilder builder = new BuildingSearchBuilder.builder()
                .setName(model.getName())
                .setNumberOfBasement(model.getNumberOfBasement())
                .setBuildingArea(model.getBuildingArea())
                .setDistrict(model.getDistrict())
                .setStreet(model.getStreet())
                .setWard(model.getWard())
                .setManagerName(model.getManagerName())
                .setManagerPhone(model.getManagerPhone())
                .setAreaRentFrom(model.getAreaRentFrom())
                .setAreaRentTo(model.getAreaRentTo())
                .setCostRentFrom(model.getCosTrentFrom())
                .setCostRentTo(model.getCosTrentTo())
                .setBuildingTypes(model.getBuildingTypes())
                .builder();
        return builder;
    }

}
