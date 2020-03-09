package com.dangvandat.controller;

import com.dangvandat.Builder.CustomerSearchBuilder;
import com.dangvandat.dto.CustomerDTO;
import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.Sorter;
import com.dangvandat.paging.impl.PageRequest;
import com.dangvandat.service.ICustomerService;
import com.dangvandat.service.impl.CustomerService;
import com.dangvandat.utils.formUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-customer"})
public class CustomerController extends HttpServlet {

    private ICustomerService customerService;

    public CustomerController(){
        if(customerService == null){
            customerService = new CustomerService();
        }
    }

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        //ObjectMapper mapper = new ObjectMapper();
        CustomerDTO model = formUtil.toModel(CustomerDTO.class , request);
        String action = request.getParameter("action");
        String url = "";
        if(action.equals("LIST")){
            url = "views/admin/customer/list.jsp";
            Pageble pageble = new PageRequest(model.getPage() , model.getMaxPageItem() , new Sorter(model.getSortName() , model.getSortBy()));
            CustomerSearchBuilder builder = initSearchCustomerBuilder(model);
            model.setTotalItems(customerService.getTotalItems(builder));
            model.setTotalPage((int) Math.ceil((double) model.getTotalItems() / model.getMaxPageItem()));
            model.setListResult(customerService.findAll(builder , pageble));
        }else if(action.equals("EDIT")){
            url = "views/admin/customer/edit.jsp";
        }
        request.setAttribute("model" , model);
        RequestDispatcher requestDispatche = request.getRequestDispatcher(url);
        requestDispatche.forward(request , response);
    }

    private CustomerSearchBuilder initSearchCustomerBuilder(CustomerDTO model){
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
