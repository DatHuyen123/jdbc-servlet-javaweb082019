package com.dangvandat.repository.impl;

import com.dangvandat.Builder.CustomerSearchBuilder;
import com.dangvandat.Entity.CustomerEntity;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.ICustomerRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository extends AbstractJDBC<CustomerEntity> implements ICustomerRepository {

    @Override
    public List<CustomerEntity> findAll(CustomerSearchBuilder builder , Pageble pageble) {
        Map<String , Object> properties = builMapSearchCustomer(builder);
        StringBuilder whereClause = builStringBuilderWhereClause(builder);
        return findAll(properties , pageble , whereClause.toString());
    }

    @Override
    public int countByProperty(CustomerSearchBuilder builder) {
        Map<String , Object> properties = builMapSearchCustomer(builder);
        StringBuilder whereClause = builStringBuilderWhereClause(builder);
        return countByProperty(properties , whereClause.toString());
    }

    private StringBuilder builStringBuilderWhereClause(CustomerSearchBuilder builder) {
        StringBuilder whereClause = new StringBuilder("");
        if(builder.getStaffId() != null){
            whereClause.append(" AND EXISTS \n" +
                    "(SELECT * FROM customer_building c WHERE c.customerid = A.id AND EXISTS (\n" +
                    "SELECT * FROM building b WHERE 1=1 AND EXISTS \n" +
                    "(SELECT * FROM assignmentbuilding a \n" +
                    "WHERE (a.buildingid = b.id AND a.staffid = "+builder.getStaffId()+"))));");
        }
        return whereClause;
    }

    private Map<String, Object> builMapSearchCustomer(CustomerSearchBuilder builder) {
        Map<String , Object> result = new HashMap<>();
        try{
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for (Field field : fields){
                if(!field.getName().equals("staffId")){
                    field.setAccessible(true);
                    if(field.get(builder) != null){
                        result.put(field.getName().toLowerCase() , field.get(builder));
                    }
                }
            }
        }catch (IllegalArgumentException | IllegalAccessException e){
            e.printStackTrace();
        }
        return result;
    }



   /* private Map<String, Object> builMapSearch(BuildingSearchBuilder builder) {
        Map<String , Object> result = new HashMap<>();
        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field field : fields){
                if(!field.getName().equals("buildingTypes")
                        && !field.getName().startsWith("costRent")
                        && !field.getName().startsWith("areaRent")){
                    field.setAccessible(true);
                    if (field.get(builder) != null){
                        if(field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")){
                            result.put(field.getName().toLowerCase() , Integer.parseInt((String) field.get(builder)));
                        }else{
                            result.put(field.getName().toLowerCase() , field.get(builder));
                        }
                    }
                }
            }
        }
        catch (IllegalArgumentException | IllegalAccessException e){
            e.printStackTrace();
        }
        return result;*/
}
