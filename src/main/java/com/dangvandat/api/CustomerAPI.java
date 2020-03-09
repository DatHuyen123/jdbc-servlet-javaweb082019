package com.dangvandat.api;

import com.dangvandat.Builder.CustomerSearchBuilder;
import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.impl.PageRequest;
import com.dangvandat.dto.CustomerDTO;
import com.dangvandat.service.ICustomerService;
import com.dangvandat.service.iBuildingService;
import com.dangvandat.service.impl.BuildingService;
import com.dangvandat.service.impl.CustomerService;
import com.dangvandat.utils.HttpUtil;
import com.dangvandat.utils.formUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-customer"})
public class CustomerAPI extends HttpServlet {

    private iBuildingService buildingService;

    private ICustomerService customerService;

    public CustomerAPI(){
        if(buildingService == null){
            buildingService = new BuildingService();
        }
        if(customerService == null){
            customerService = new CustomerService();
        }
    }

    protected void doGet(HttpServletRequest request , HttpServletResponse reponse)
            throws ServletException , IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("utf-8");
        reponse.setContentType("application/json");
        CustomerDTO model = formUtil.toModel(CustomerDTO.class , request);
        CustomerSearchBuilder builder = initSearchCustomer(model);
        Pageble pageble = new PageRequest(null , null , null);
        model.setListResult(customerService.findAll(builder , pageble));
        mapper.writeValue(reponse.getOutputStream(), model.getListResult());
    }

    protected void doPost(HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CustomerDTO customerDTO = HttpUtil.of(request.getReader()).toModel(CustomerDTO.class);
        customerDTO = customerService.save(customerDTO);
        mapper.writeValue(response.getOutputStream() , customerDTO);
    }

    protected void doPut(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        CustomerDTO updateCustomerDTO = HttpUtil.of(request.getReader()).toModel(CustomerDTO.class);
        customerService.update(updateCustomerDTO , updateCustomerDTO.getId());
        mapper.writeValue(response.getOutputStream() , "{}");
    }

    protected void doDelete(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        CustomerDTO deleteCustomer = HttpUtil.of(request.getReader()).toModel(CustomerDTO.class);
        customerService.delete(deleteCustomer.getIds());
        mapper.writeValue(response.getOutputStream() , "{}");
    }

    private CustomerSearchBuilder initSearchCustomer(CustomerDTO model) {
        CustomerSearchBuilder builder = new CustomerSearchBuilder.builder()
                .setCompanyName(model.getCompanyName())
                .setFullName(model.getFullName())
                .setEmail(model.getEmail())
                .setPhone(model.getPhone())
                .setStaffId(model.getStaffId())
                .builder();
        return builder;
    }
}
