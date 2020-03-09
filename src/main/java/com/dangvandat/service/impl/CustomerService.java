package com.dangvandat.service.impl;

import com.dangvandat.Builder.CustomerSearchBuilder;
import com.dangvandat.converter.CustomerConverter;
import com.dangvandat.dto.CustomerDTO;
import com.dangvandat.Entity.CustomerEntity;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.impl.CustomerRepository;
import com.dangvandat.repository.ICustomerRepository;
import com.dangvandat.service.ICustomerService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService implements ICustomerService {

    private ICustomerRepository customerRepository = new CustomerRepository();

    private CustomerConverter customerConverter = new CustomerConverter();

    @Override
    public List<CustomerDTO> findAll(CustomerSearchBuilder builder , Pageble pageble) {
        List<CustomerEntity> customerEntities = customerRepository.findAll(builder , pageble);
        List<CustomerDTO> results = customerEntities.stream().map(item -> customerConverter.convertToDTO(item)).collect(Collectors.toList());
        return results;
    }

    @Override
    public int getTotalItems(CustomerSearchBuilder builder) {
        return customerRepository.countByProperty(builder);
    }

    @Override
    public CustomerDTO save(CustomerDTO insertCustomerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(insertCustomerDTO);
        customerEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        customerEntity.setCreatedBy("");

        Long id = customerRepository.insert(customerEntity);
        return customerConverter.convertToDTO(customerRepository.findById(id));
    }

    @Override
    public void update(CustomerDTO updateCustomerDTO, Long id){
        CustomerEntity oldCustomerEntity = customerRepository.findById(id);
        CustomerEntity newCustomerEnntity = customerConverter.convertToEntity(updateCustomerDTO);
        newCustomerEnntity.setCreatedBy(oldCustomerEntity.getCreatedBy());
        newCustomerEnntity.setCreatedDate(oldCustomerEntity.getCreatedDate());

        customerRepository.update(newCustomerEnntity);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long item : ids){
            customerRepository.delete(item);
        }
    }
}
