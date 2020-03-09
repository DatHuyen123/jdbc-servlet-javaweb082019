package com.dangvandat.repository;

import com.dangvandat.Builder.CustomerSearchBuilder;
import com.dangvandat.Entity.CustomerEntity;
import com.dangvandat.paging.Pageble;

import java.util.List;

public interface ICustomerRepository extends GernericJDBC<CustomerEntity> {
    List<CustomerEntity> findAll(CustomerSearchBuilder builder , Pageble pageble);
    int countByProperty(CustomerSearchBuilder builder);
}
