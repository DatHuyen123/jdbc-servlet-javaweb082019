package com.dangvandat.service;

import com.dangvandat.Builder.CustomerSearchBuilder;
import com.dangvandat.dto.CustomerDTO;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> findAll(CustomerSearchBuilder builder , Pageble pageble);
    int getTotalItems(CustomerSearchBuilder builder);
    CustomerDTO save(CustomerDTO insertCustomerDTO);
    void update(CustomerDTO updateCustomerDTO , Long id);
    void delete(Long[] ids);
}
